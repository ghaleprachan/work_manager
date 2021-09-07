package app.prgghale.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.widget.Toast
import androidx.work.*
import app.prgghale.workmanager.databinding.ActivityMainBinding


/**
 * Sample from
 *
 * https://blog.mindorks.com/integrating-work-manager-in-android*/
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.e("INOUT", "Working")

        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // To Pass Data
        //val message = workDataOf("Prachan Ghale My personal data" to String)


        // Create work request which defines how and when work should run
        // There are two types of work:
        // 1. OneTimeWorkRequest - Runs the task only once
        // 2. PeriodicWorkTimeRequest - Runs the task after a certain time interval.

        val myWorkRequest = OneTimeWorkRequestBuilder<WorkMangerWorker>()
            .setConstraints(myConstraints)
            //.setInputData(message)
            .build()

        // If we don't want to start work manager immediately we can set initial delay like below
        /*val yourWorkRequest = OneTimeWorkRequestBuilder<YourWorkerClass>()
            .setInitialDelay(10, TimeUnit.MINUTES)
            .build()*/

        /* Periodic Task
        val yourPeriodicWorkRequest =
            PeriodicWorkRequestBuilder<YourPeriodicWorkerClass>(1, TimeUnit.HOURS)
            .setConstraints(myConstraints)
            .build()
        * */

        // We also can do different customization in work manager using
        /*
        val myConstraints = Constraints.Builder()
                .setRequiresDeviceIdle(true) //checks whether device should be idle for the WorkRequest to run
                .setRequiresCharging(true) //checks whether device should be charging for the WorkRequest to run
                .setRequiredNetworkType(NetworkType.CONNECTED) //checks whether device should have Network Connection
                .setRequiresBatteryNotLow(true) // checks whether device battery should have a specific level to run the work request
                .setRequiresStorageNotLow(true) // checks whether device storage should have a specific level to run the work request
                .build()
        * */

        // We can start work manager using
        WorkManager.getInstance(applicationContext).enqueue(myWorkRequest)

        // NOTE if we want to do some task when work-manager executes successfully
        // We can do like below
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(myWorkRequest.id)
            .observe(this, {
                if (it != null && it.state == WorkInfo.State.SUCCEEDED) {
                    Toast.makeText(this, "Work manager work successful", Toast.LENGTH_SHORT).show()
                } else if (it.state == WorkInfo.State.ENQUEUED) {
                    Toast.makeText(this, "Work Started", Toast.LENGTH_SHORT).show()
                }
            })

        // To cancel the task we use
        // WorkManager.cancelWorkById(workRequest.id)

    }
}