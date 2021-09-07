package app.prgghale.workmanager

import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf


/**
Now, as a next step, we will create a Worker class.
Worker class is responsible to perform work synchronously
on a background thread provided by the work manager.*/
class WorkMangerWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.e("INOUT", " this is my message")
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
    }
}