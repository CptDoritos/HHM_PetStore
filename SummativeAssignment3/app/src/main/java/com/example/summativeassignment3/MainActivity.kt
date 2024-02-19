package com.example.summativeassignment3

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*

class MainActivity : AppCompatActivity() {
    var checkpoint:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        checkpoint = 1

        val btnEdit: Button = findViewById(R.id.btnEdit)

        btnEdit.setOnClickListener {
            goToPassword()
        }

        val btnAbout: Button = findViewById(R.id.btnAboutUs)

        btnAbout.setOnClickListener {
            AboutUsLayout()
        }
    }

    fun goToPassword(){
        setContentView(R.layout.password_layout)
        val inputPassword: EditText = findViewById(R.id.editTextPassword)
        val btnCheck: Button = findViewById(R.id.btnCheckPassword)

        btnCheck.setOnClickListener{
            if(inputPassword.text.toString() == "Better Call Saul!"){
                Toast.makeText(this, "Correct Password Entered!", Toast.LENGTH_LONG).show()
                goToChoice()
            }else{
                Toast.makeText(this, "Incorrect Password Entered!", Toast.LENGTH_LONG).show()
                inputPassword.text = null
            }
        }
        checkpoint = 2
    }

    fun goToChoice(){
        setContentView(R.layout.crud_operations_choice_layout)

        val chooseDisplay: Button = findViewById(R.id.chooseDisplay)
        val chooseInsert: Button = findViewById(R.id.chooseInsert)
        val chooseUpdate: Button = findViewById(R.id.chooseUpdate)
        val chooseDelete: Button = findViewById(R.id.chooseDelete)

        chooseDisplay.setOnClickListener {
            setContentView(R.layout.frag_display_choice)
            checkpoint = 4
        }

        chooseInsert.setOnClickListener {
            setContentView(R.layout.frag_insert_choice)
            checkpoint = 4
        }

        chooseUpdate.setOnClickListener {
            setContentView(R.layout.frag_update_choice)
            checkpoint = 4
        }

        chooseDelete.setOnClickListener {
            setContentView(R.layout.frag_delete_choice)
            checkpoint = 4
        }

        checkpoint = 3
    }

    fun backToMain(v: View){
        setContentView(R.layout.main_menu)
        checkpoint = 1
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu_layout, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menuItem1) {
            Toast.makeText(this, "Selected Item: About Us", Toast.LENGTH_SHORT).show()
            AboutUsLayout()
            return true
        }

        if (item.itemId == R.id.menuItem2) {
            Toast.makeText(this, "Selected Item: Back To Main Menu", Toast.LENGTH_SHORT).show()
            setContentView(R.layout.main_menu)
            checkpoint = 1
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun SwitchToInsertPet(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragInsert()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragInsert()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }
        checkpoint = 5
    }

    fun SwitchToInsertClient(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragInsertClient()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragInsertClient()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }
        checkpoint = 5
    }

    fun SwitchToInsertPurchase(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragInsertPurchase()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragInsertPurchase()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }
        checkpoint = 2
    }

    fun SwitchToDisplayPet(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragDisplay()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragDisplay()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }
        checkpoint = 6
    }

    fun SwitchToDisplayClient(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragDisplayClient()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragDisplayClient()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }
        checkpoint = 6
    }

    fun SwitchToDisplayPurchase(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragDisplayPurchase()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragDisplayPurchase()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }
        checkpoint = 2
    }

    fun SwitchToUpdatePet(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragUpdate()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragUpdate()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }

        checkpoint = 7
    }

    fun SwitchToUpdateClient(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragUpdateClient()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragUpdateClient()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }

        checkpoint = 7
    }

    fun SwitchToUpdatePurchase(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragUpdatePurchase()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragUpdatePurchase()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }

        checkpoint = 7
    }

    fun SwitchToDeletePet(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragDelete()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragDelete()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }

        checkpoint = 8
    }

    fun SwitchToDeleteClient(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragDeleteClient()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragDeleteClient()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }

        checkpoint = 8
    }

    fun SwitchToDeletePurchase(v: View){
        setContentView(R.layout.activity_main)
        var frag = supportFragmentManager
            .findFragmentById(R.id.fragmentHolder)

        if(frag == null){
            frag = FragDeletePurchase()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentHolder, frag)
                .commit()
        }else{
            frag = FragDeletePurchase()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frag)
                .commit()
        }

        checkpoint = 8
    }

    fun AboutUsLayout(){
        setContentView(R.layout.about_me)


        val videoRdioBtn: RadioButton = findViewById(R.id.RdioBtnVideo)
        val videoView: VideoView = findViewById(R.id.videoView1)
        videoView.visibility = View.GONE
        val MediaControls: MediaController = MediaController(this)
        val imageRdioBtn: RadioButton = findViewById(R.id.RdioBtnImages)
        val checkRdioBtn: Button = findViewById(R.id.checkRdioBtn)

        val RdioGroupImageContent: RadioGroup = findViewById(R.id.RdioGroupImagesContent)
        RdioGroupImageContent.visibility = View.GONE

        checkRdioBtn.setOnClickListener {
            if(videoRdioBtn.isChecked()){
                videoView.visibility = View.VISIBLE
                MediaControls!!.setAnchorView(this.findViewById(R.id.videoView1))
                videoView!!.setMediaController(MediaControls)

                val uri: Uri = Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.videoplaybackcat)
                videoView!!.setVideoURI(uri)
                videoView!!.requestFocus()
                videoView!!.start()
            }else{
                videoView.visibility = View.GONE
            }

            if(imageRdioBtn.isChecked()){
                RdioGroupImageContent.visibility = View.VISIBLE
            }else{
                RdioGroupImageContent.visibility = View.GONE
            }
        }
        checkpoint = 2
    }

    override fun onBackPressed() {

        if(checkpoint == 1){

        }else if(checkpoint == 0){
            Toast.makeText(application, "Unknown Error", Toast.LENGTH_LONG).show()
        }else if(checkpoint == 2){
            setContentView(R.layout.main_menu)
            checkpoint = 1
            val btnEdit: Button = findViewById(R.id.btnEdit)

            btnEdit.setOnClickListener {
                goToPassword()
                checkpoint = 2
            }
            val btnAbout: Button = findViewById(R.id.btnAboutUs)
            btnAbout.setOnClickListener {
                AboutUsLayout()
                checkpoint = 2
            }
        }else if(checkpoint == 3){
            goToPassword()
            checkpoint = 2
        }else if(checkpoint == 4){
            goToChoice()
            checkpoint = 3
        }else if(checkpoint == 5){
            setContentView(R.layout.frag_insert_choice)
            checkpoint = 4
        }else if(checkpoint == 6){
            setContentView(R.layout.frag_display_choice)
            checkpoint = 4
        }else if(checkpoint == 7){
            setContentView(R.layout.frag_update_choice)
            checkpoint = 4
        }else if(checkpoint == 8){
            setContentView(R.layout.frag_delete_choice)
            checkpoint = 4
        }else{
            super.onBackPressed()
        }
    }
}