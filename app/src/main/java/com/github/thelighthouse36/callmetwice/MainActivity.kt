/*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

package com.github.thelighthouse36.callmetwice

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.github.thelighthouse36.callmetwice.commons.base.BaseActivity
import com.github.thelighthouse36.callmetwice.commons.events.MessageEvent
import com.github.thelighthouse36.callmetwice.commons.events.PermissionDenied
import com.github.thelighthouse36.callmetwice.commons.events.PhoneManifestPermissionsEnabled
import com.github.thelighthouse36.callmetwice.commons.utils.CapabilitiesRequestorImpl
import com.github.thelighthouse36.callmetwice.commons.utils.ManifestPermissionRequesterImpl
import com.github.thelighthouse36.callmetwice.ui.main.UiPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_log.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import pub.devrel.easypermissions.AppSettingsDialog
import java.lang.ref.WeakReference


class MainActivity : BaseActivity() {

    private val manifestPermissionRequestor = ManifestPermissionRequesterImpl()

    private val capabilitiesRequestor = CapabilitiesRequestorImpl()

    val TAG = "TAGBAY"


    // flag that restarts checking capabilities dialog, after user enables manifest permissions
    // via app settings page
    private var checkCapabilitiesOnResume = false

    lateinit var bubbleLoop: Runnable
    lateinit var bubbleHandler: Handler

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "STARTING")
        setContentView(R.layout.activity_main)

        val fragmentAdapter = UiPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)


        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        listenUiEvents()
        //
        manifestPermissionRequestor.activity = WeakReference(this)
        capabilitiesRequestor.activityReference = WeakReference(this)
        //
        manifestPermissionRequestor.getPermissions()

        bubbleHandler = Handler(Looper.getMainLooper())



        //bubble environment loop
        bubbleLoop = Runnable {
            val iterator = bubbleEnvironment.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.life <= 0) {
                    iterator.remove()
                } else {
                    item.life--
                }
            }

            fragmentAdapter.bubbleFragment.refresh()


            bubbleHandler.postDelayed(bubbleLoop, 1000)
        }
        bubbleHandler.postDelayed(bubbleLoop, 1000)
//        exec.scheduleAtFixedRate(bubbleLoop, 0, 1, TimeUnit.SECONDS)


    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        if (checkCapabilitiesOnResume) {
            capabilitiesRequestor.invokeCapabilitiesRequest()
            checkCapabilitiesOnResume = false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        manifestPermissionRequestor.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        capabilitiesRequestor.onActivityResult(requestCode, resultCode, data)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun listenUiEvents() {
        uiEvent.observe(this, {
            when (it) {
                is PermissionDenied -> {
                    checkCapabilitiesOnResume = true
                    // This will display a dialog directing them to enable the permission in app settings.
                    AppSettingsDialog.Builder(this).build().show()
                }
                is PhoneManifestPermissionsEnabled -> {
                    // now we can load phone dialer capabilities requests
                    capabilitiesRequestor.invokeCapabilitiesRequest()
                }
                else -> {
                    // NOOP
                }
            }
        })
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        val newText = String.format("%s%s\n", textLog.text.toString(), event.message)
        textLog.setText(newText)
    }



}
