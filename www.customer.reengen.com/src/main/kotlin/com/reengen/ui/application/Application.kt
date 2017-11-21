package com.reengen.ui.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication


/**
 * Created by erguzelolgun on 14/07/2017.
 */
@SpringBootApplication
class Application

fun main(args:Array<String>){

    SpringApplication.run(Application::class.java, *args)
}
