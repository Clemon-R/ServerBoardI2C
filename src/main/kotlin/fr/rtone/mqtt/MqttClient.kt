package fr.rtone.mqtt

import org.eclipse.paho.client.mqttv3.*
import java.lang.Exception

class MqttClient() : Runnable{
	private lateinit var serverUrl: String

	private val client by lazy {
		val clientId = org.eclipse.paho.client.mqttv3.MqttClient.generateClientId()
		MqttClient(serverUrl, clientId)
	}
	private val handler = MqttClientHandler()

	companion object{
		const val TAG = "Client"
		const val RETRY_CONNECT = 10
		var instance: MqttClient? = null
	}

	constructor(url: String) : this() {
		serverUrl = url
		instance = this
	}

	override fun run() {
		connect()
	}

	fun connect() : Boolean{
		try{
			client.setCallback(object: MqttCallbackExtended {
				private var retry: Int = 0
				override fun connectComplete(reconnect: Boolean, serverURI: String?) {
					retry = 0
					println("$TAG: Connected on $serverURI, need to reconnect ? $reconnect")
					println("$TAG: Subscribing to /demo/rtone/esp32/data ...")
					client.subscribe("/demo/rtone/esp32/data", 0
					) { topic, msg -> handler.parsePacket(topic, msg.payload) }
				}

				override fun connectionLost(cause: Throwable?) {
					cause?.printStackTrace()
					println("$TAG: Connectiong lost")
					if (retry < RETRY_CONNECT) {
						println("$TAG: Trying to reconnect...")
						client.connect()
						retry++
					} else {
						println("$TAG: After $retry chances, we failed to reconnect")
					}
				}

				override fun deliveryComplete(token: IMqttDeliveryToken?) {
					println("$TAG: MessageReceiver delivered")
				}

				override fun messageArrived(topic: String?, message: MqttMessage?) {
				}
			})
			println("$TAG: Connecting...")
			client.connect()
		}catch (e: Exception){
			e.printStackTrace()
			return false
		}
		return true
	}

	fun publishMessage(topic: String, msg: String) : Boolean
	{
		try {
			val message = MqttMessage()
			message.payload = msg.toByteArray()
			println("$TAG -> topic $topic: $msg")
			client.publish(topic, message)
		} catch (e: MqttException) {
			println("$TAG: Error Publishing to $topic: " + e.message)
			e.printStackTrace()
			return false
		}
		return true
	}

	fun close() : Boolean
	{
		try{
			println("$TAG: Closing the client...")
			client.close()
		}catch (e: Exception){
			e.printStackTrace()
			return false
		}
		return true
	}

	fun getHandler() : MqttClientHandler
	{
		return handler
	}
}