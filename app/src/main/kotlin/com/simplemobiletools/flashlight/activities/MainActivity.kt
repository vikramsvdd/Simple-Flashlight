package com.simplemobiletools.flashlight.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.ShortcutInfo
import android.graphics.drawable.Icon
import android.graphics.drawable.LayerDrawable
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.simplemobiletools.commons.extensions.*
import com.simplemobiletools.commons.helpers.LICENSE_EVENT_BUS
import com.simplemobiletools.commons.helpers.PERMISSION_CAMERA
import com.simplemobiletools.commons.helpers.isNougatMR1Plus
import com.simplemobiletools.commons.helpers.isNougatPlus
import com.simplemobiletools.commons.models.FAQItem
import com.simplemobiletools.flashlight.BuildConfig
import com.simplemobiletools.flashlight.R
import com.simplemobiletools.flashlight.extensions.config
import com.simplemobiletools.flashlight.helpers.MyCameraImpl
import com.simplemobiletools.flashlight.models.Events
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*
import java.util.concurrent.TimeUnit ;
import kotlin.system.exitProcess
import kotlin.text.split as split
import java.util.regex.Pattern
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor



class MainActivity : SimpleActivity() {
    private val MAX_STROBO_DELAY = 2000L
    private val MIN_STROBO_DELAY =1L
    private val FLASHLIGHT_STATE = "flashlight_state"
    private val STROBOSCOPE_STATE = "stroboscope_state"

    private var mBus: EventBus? = null
    private var mCameraImpl: MyCameraImpl? = null
    private var mIsFlashlightOn = false
    private var reTurnFlashlightOn = true
    lateinit var tvAuthStatus:TextView
    lateinit var executor: Executor
    lateinit var biometricPrompt:androidx.biometric.BiometricPrompt
    lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appLaunched(BuildConfig.APPLICATION_ID)
        mBus = EventBus.getDefault()
        //changeIconColor(getContrastColor(), stroboscope_btn)
        //biometric details from here-on













        bright_display_btn.setOnClickListener {
            reTurnFlashlightOn = false
           // val stream = arrayOf("1","0","1","0","0","1","0","1")
            val stream=arrayOf('0', '1', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '1', '1', '0', '1', '1', '0', '0', '0', '1', '1', '0', '1', '1', '0', '0', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '0', '1', '0', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '0', '0', '1', '1', '0', '1', '1', '0', '0', '0', '1', '1', '0', '0', '1', '0', '0')
            //val stream=arrayOf("01", "10", "00", "11", "01", "10", "10", "00", "01", "10", "11", "11", "01", "10", "00", "11", "01", "10", "10", "11", "01", "10", "00", "01", "01", "10", "11", "00", "01", "10", "10", "01", "01", "10", "11", "10", "01", "10", "01", "11", "01", "10", "00", "01", "01", "10", "11", "01")
            //val stream=arrayOf("0110", "0011", "0110", "1000", "0110", "1111", "0110", "0011", "0110", "1011", "0110", "0001", "0110", "1100", "0110", "1001", "0110", "1110", "0110", "0111", "0110", "0001", "0110", "1101")
           //' biometricPrompt.authenticate(promptInfo)
            //if(tvAuthStatus.text=="Sucess"){
                // j loop starting for( j in 1..10) {
             var ctr=0
            //symbol assigning based on frequency of occurence of elements in the array
                     for (i in stream) {
                         if (i == '0') {
                             mCameraImpl!!.enableFlashlight()
                             System.nanoTime()
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()
                             ctr += 1
                         }                                                       // using variable PWM
                         if (i == '1') {
                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(17)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1
                         }

                         /*if(i=="0001"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(34)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }

                         if(i=="1000"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(51)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }

                         if(i=="1111"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(68)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }

                         if(i=="1011"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(85)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }

                         if(i=="1100"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(102)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }
                         if(i=="1001"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(119)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }

                         if(i=="1110"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(136)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }

                         if(i=="0111"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(153)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }

                         if(i=="1101"){

                             mCameraImpl!!.enableFlashlight()
                             TimeUnit.MILLISECONDS.sleep(170)
                             mCameraImpl!!.disableFlashlight()
                             System.nanoTime()   // code for switching on and off the LED, corresponding to second button in the app and the switching happens according to the sequence in the array
                             ctr += 1


                         }*/












                         /* if (ctr == stream.size) {
                        exitProcess(-1)
                    }*/
                     }
            //}
                 // j loop ending}
            /*for( i in 1..50){
                //val start = System.currentTimeMillis()
                mCameraImpl!!.enableFlashlight()
                TimeUnit.MICROSECONDS.sleep(1)
               // val end = System.currentTimeMillis()
               // println("The Value in ms is : ${end - start} ms")
                mCameraImpl!!.disableFlashlight()
                TimeUnit.MICROSECONDS.sleep(1)


            }*/


            // startActivity(Intent(applicationContext, BrightDisplayActivity::class.java))
        }

        /*flashlight_btn.setOnClickListener {
            mCameraImpl!!.toggleFlashlight()
        }

        sos_btn.setOnClickListener {
            toggleStroboscope(true)
        }

        stroboscope_btn.setOnClickListener {
            toggleStroboscope(false)
        }

       // setupStroboscope()
        //checkAppOnSDCard()*/
    }

    override fun onResume() {
        super.onResume()
        mCameraImpl!!.handleCameraSetup()
        checkState(MyCameraImpl.isFlashlightOn)

        val contrastColor = getContrastColor()
        changeIconColor(contrastColor, bright_display_btn)
        bright_display_btn.beVisibleIf(config.brightDisplay)
        //sos_btn.beVisibleIf(config.sos)
        //sos_btn.setTextColor(contrastColor)
        //stroboscope_btn.beVisibleIf(config.stroboscope)

        if (!config.stroboscope) {
            mCameraImpl!!.stopStroboscope()
            stroboscope_bar.beInvisible()
        }

        updateTextColors(main_holder)
        if (stroboscope_bar.isInvisible()) {
            //changeIconColor(contrastColor, stroboscope_btn)
        }

        requestedOrientation = if (config.forcePortraitMode) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT else ActivityInfo.SCREEN_ORIENTATION_SENSOR
        invalidateOptionsMenu()

        if (config.turnFlashlightOn && reTurnFlashlightOn) {
            mCameraImpl!!.enableFlashlight()
        }

        reTurnFlashlightOn = true

        checkShortcuts()
    }

    override fun onStart() {
        super.onStart()
        mBus!!.register(this)

        if (mCameraImpl == null) {
            setupCameraImpl()
        }
    }

    override fun onStop() {
        super.onStop()
        mBus!!.unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseCamera()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        updateMenuItemColors(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> launchSettings()
            R.id.about -> launchAbout()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(FLASHLIGHT_STATE, mIsFlashlightOn)
        outState.putBoolean(STROBOSCOPE_STATE, stroboscope_bar.isVisible())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val isFlashlightOn = savedInstanceState.getBoolean(FLASHLIGHT_STATE, false)
        if (isFlashlightOn) {
            mCameraImpl!!.toggleFlashlight()
        }

        val isStroboscopeOn = savedInstanceState.getBoolean(STROBOSCOPE_STATE, false)
        if (isStroboscopeOn) {
            toggleStroboscope(false )

        }
    }

    private fun launchSettings() {
        hideKeyboard()
        reTurnFlashlightOn = false
        startActivity(Intent(applicationContext, SettingsActivity::class.java))
    }

    private fun launchAbout() {
        reTurnFlashlightOn = false
        val licenses = LICENSE_EVENT_BUS

        val faqItems = arrayListOf(
            FAQItem(R.string.faq_1_title_commons, R.string.faq_1_text_commons),
            FAQItem(R.string.faq_4_title_commons, R.string.faq_4_text_commons),
            FAQItem(R.string.faq_2_title_commons, R.string.faq_2_text_commons),
            FAQItem(R.string.faq_6_title_commons, R.string.faq_6_text_commons)
        )

        startAboutActivity(R.string.app_name, licenses, BuildConfig.VERSION_NAME, faqItems, true)
    }

    private fun setupCameraImpl() {
        mCameraImpl = MyCameraImpl.newInstance(this)
        if (config.turnFlashlightOn) {
            mCameraImpl!!.enableFlashlight()
        }
    }

    private fun setupStroboscope() {
        stroboscope_bar.max = (MAX_STROBO_DELAY - (MIN_STROBO_DELAY)).toInt()
        stroboscope_bar.progress = config.stroboscopeProgress
        stroboscope_bar.onSeekBarChangeListener { progress ->
            val frequency = stroboscope_bar.max - progress + (MIN_STROBO_DELAY)
            mCameraImpl?.stroboFrequency = frequency
            config.stroboscopeFrequency = frequency
            config.stroboscopeProgress = progress
        }
    }

    private fun toggleStroboscope(isSOS: Boolean) {
        // use the old Camera API for stroboscope, the new Camera Manager is way too slow
        if (isNougatPlus()) {
            cameraPermissionGranted(isSOS)
        } else {
            handlePermission(PERMISSION_CAMERA) {
                if (it) {
                    cameraPermissionGranted(isSOS)
                } else {
                    toast(R.string.camera_permission)
                }
            }
        }
    }

    private fun cameraPermissionGranted(isSOS: Boolean) {
        if (isSOS) {
            val isSOSRunning = mCameraImpl!!.toggleSOS()
            //sos_btn.setTextColor(if (isSOSRunning) getAdjustedPrimaryColor() else getContrastColor())
        } else {
            if (mCameraImpl!!.toggleStroboscope()) {
                stroboscope_bar.beInvisibleIf(stroboscope_bar.isVisible())
                //changeIconColor(if (stroboscope_bar.isVisible()) getAdjustedPrimaryColor() else getContrastColor(), stroboscope_btn)
            }
        }
    }

    private fun getContrastColor() = config.backgroundColor.getContrastColor()

    private fun releaseCamera() {
        mCameraImpl?.releaseCamera()
        mCameraImpl = null
    }

    @Subscribe
    fun stateChangedEvent(event: Events.StateChanged) {
        checkState(event.isEnabled)
    }

    @Subscribe
    fun stopStroboscope(event: Events.StopStroboscope) {
        stroboscope_bar.beInvisible()
       // changeIconColor(getContrastColor(), stroboscope_btn)
    }

    @Subscribe
    fun stopSOS(event: Events.StopSOS) {
        //sos_btn.setTextColor(getContrastColor())
    }

    private fun checkState(isEnabled: Boolean) {
        if (isEnabled) {
            enableFlashlight()
        } else {
            disableFlashlight()
        }
    }

    private fun enableFlashlight() {
        changeIconColor(getAdjustedPrimaryColor(), bright_display_btn)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        mIsFlashlightOn = true

       // sos_btn.setTextColor(getContrastColor())

        //changeIconColor(getContrastColor(), stroboscope_btn)
        stroboscope_bar.beInvisible()
    }

    private fun disableFlashlight() {
        changeIconColor(getContrastColor(), bright_display_btn)
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        mIsFlashlightOn = false
    }

    private fun changeIconColor(color: Int, imageView: ImageView?) {
        imageView!!.background.mutate().applyColorFilter(color)
    }

    @SuppressLint("NewApi")
    private fun checkShortcuts() {
        val appIconColor = config.appIconColor
        if (isNougatMR1Plus() && config.lastHandledShortcutColor != appIconColor) {
            val createNewContact = getBrightDisplayShortcut(appIconColor)

            try {
                shortcutManager.dynamicShortcuts = Arrays.asList(createNewContact)
                config.lastHandledShortcutColor = appIconColor
            } catch (ignored: Exception) {
            }
        }
    }

    @SuppressLint("NewApi")
    private fun getBrightDisplayShortcut(appIconColor: Int): ShortcutInfo {
        val brightDisplay = getString(R.string.bright_display)
        val drawable = resources.getDrawable(R.drawable.shortcut_bright_display)
        (drawable as LayerDrawable).findDrawableByLayerId(R.id.shortcut_bright_display_background).applyColorFilter(appIconColor)
        val bmp = drawable.convertToBitmap()

        val intent = Intent(this, BrightDisplayActivity::class.java)
        intent.action = Intent.ACTION_VIEW
        return ShortcutInfo.Builder(this, "bright_display")
            .setShortLabel(brightDisplay)
            .setLongLabel(brightDisplay)
            .setIcon(Icon.createWithBitmap(bmp))
            .setIntent(intent)
            .build()
    }

    @Subscribe
    fun cameraUnavailable(event: Events.CameraUnavailable) {
        toast(R.string.camera_error)
        disableFlashlight()
    }
}


