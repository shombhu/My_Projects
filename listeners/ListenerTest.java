package com.ecrm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ecrm.base.TestBase;
import com.ecrm.util.Log;
/**
 *
 *  This is the ListenerTest Class extends Base Class implements ITestListener interface
 *
 */
public class ListenerTest extends TestBase implements ITestListener
{



	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 *
	 * This method will execute when Test is started
	 */

	@Override
	public void onTestStart(ITestResult result)
	{


	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 *
	 * This method will execute when Test is Success
	 *
	 */

	@Override
	public void onTestSuccess(ITestResult result)
	{



	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 *
	 * This method will execute when Test is Failure
	 *
	 */

	@Override
	public void onTestFailure(ITestResult result)
	{

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 *
	 *  This method will execute when Test is Skipped
	 *
	 */
	@Override
	public void onTestSkipped(ITestResult result)
	{


	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
	 *
	 * This method will execute when Test is FailedButWithinSuccessPercentage
	 *
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{



	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 *
	 * This method will execute when Test is Started
	 */

	@Override
	public void onStart(ITestContext context)
	{

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 *
	 * This method will execute when Test is Finish
	 *
	 */

	@Override
	public void onFinish(ITestContext context)
	{
		Log.endFeature("Test Script has ended");

		//driver.quit();
	}
}
