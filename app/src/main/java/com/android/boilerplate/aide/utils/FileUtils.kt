package com.android.boilerplate.aide.utils

import android.content.Context
import com.android.boilerplate.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Abdul Rahman
 */
object FileUtils {

    private const val FILENAME_FORMAT = "yyyyMMddHHmmssSSS"
    private const val PHOTO_EXTENSION = ".jpg"
    private const val VIDEO_EXTENSION = ".mp4"

    /**
     * Generate name for Image File
     */
    fun getImageFileName(context: Context): String {
        context.apply {
            return getString(R.string.app_name_uppercase).plus(getString(R.string.image)).plus(
                SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
            ).plus(PHOTO_EXTENSION)
        }
    }

    /**
     * Generate name for Video File
     */
    fun getVideoFileName(context: Context): String {
        context.apply {
            return getString(R.string.app_name_uppercase).plus(getString(R.string.video)).plus(
                SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
            ).plus(VIDEO_EXTENSION)
        }
    }

    /** Helper function used to create a timestamped file */
    fun createFile(baseFolder: File, fileName: String): File {
        return File(baseFolder, fileName)
    }
}