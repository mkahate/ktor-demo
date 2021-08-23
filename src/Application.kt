package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Authentication) {
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/note/{id}") {
            val id=call.parameters["id"]
            call.respond("${id}")
        }

        get("/note"){
            val id=call.request.queryParameters["id"]
            call.respond("${id}")
        }

        route("/notes"){
            route("/create"){
                post {
                    val body=call.receive<String>()
                    call.respond(body)
                }
            }


            delete{
                val body=call.receive<String>()
                call.respond(body)
            }
        }
    }
}

