package otherTests.testng;

import mockit.usage.cut.Applet;

import org.testng.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import mockit.*;

public class BaseTestNGDecoratorTest implements IHookable
{
   // Makes sure TestNG integration works with test classes which implement IHookable.
   @Override
   public void run(IHookCallBack callBack, ITestResult testResult) {
      callBack.runTestMethod(testResult);
   }

   public static class FakeClass1 extends MockUp<Applet> {
      @Mock
      public String getAppletInfo() { return "TEST1"; }
   }

   @BeforeMethod
   public final void beforeBase() {
      assertNull(new Applet().getAppletInfo());
      new FakeClass1();
      assertEquals(new Applet().getAppletInfo(), "TEST1");
   }

   @AfterMethod
   public final void afterBase() {
      assertEquals(new Applet().getAppletInfo(), "TEST1");
   }
}
