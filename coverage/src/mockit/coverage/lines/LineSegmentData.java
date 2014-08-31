/*
 * Copyright (c) 2006-2014 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.coverage.lines;

import java.io.*;
import java.util.*;

import org.jetbrains.annotations.*;

import mockit.coverage.*;

public class LineSegmentData implements Serializable
{
   private static final long serialVersionUID = -6233980722802474992L;
   private static final int MAX_CALL_POINTS = Integer.parseInt(Configuration.getProperty("maxCallPoints", "10"));

   // Static data:
   private boolean unreachable;

   // Runtime data:
   int executionCount;
   @Nullable private List<CallPoint> callPoints;

   public final void markAsUnreachable() { unreachable = true; }

   final boolean acceptsAdditionalCallPoints()
   {
      return callPoints == null || callPoints.size() < MAX_CALL_POINTS;
   }

   final void registerExecution(@Nullable CallPoint callPoint)
   {
      executionCount++;

      if (callPoint != null) {
         addCallPoint(callPoint);
      }
   }

   private void addCallPoint(@NotNull CallPoint callPoint)
   {
      if (callPoints == null) {
         callPoints = new ArrayList<CallPoint>(MAX_CALL_POINTS);
      }

      for (int i = callPoints.size() - 1; i >= 0; i--) {
         CallPoint previousCallPoint = callPoints.get(i);

         if (callPoint.isSameLineInTestCode(previousCallPoint)) {
            previousCallPoint.incrementRepetitionCount();
            return;
         }
      }

      callPoints.add(callPoint);
   }

   public final boolean containsCallPoints() { return callPoints != null; }
   @Nullable public final List<CallPoint> getCallPoints() { return callPoints; }

   public final int getExecutionCount() { return executionCount; }
   final void setExecutionCount(int executionCount) { this.executionCount = executionCount; }

   public final boolean isCovered() { return unreachable || executionCount > 0; }

   final void addExecutionCountAndCallPointsFromPreviousTestRun(@NotNull LineSegmentData previousData)
   {
      executionCount += previousData.executionCount;

      if (previousData.callPoints != null) {
         if (callPoints != null) {
            callPoints.addAll(0, previousData.callPoints);
         }
         else {
            callPoints = previousData.callPoints;
         }
      }
   }

   void reset() { executionCount = 0; }
}