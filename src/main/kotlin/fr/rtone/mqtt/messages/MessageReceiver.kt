package fr.rtone.mqtt.messages

import com.google.gson.annotations.SerializedName

open class MessageReceiver(
	var macAddress: String?,
	@SerializedName("mac") var macGateway: String,
	@SerializedName("type") var type: String
){
	constructor() : this("", "", "")
}