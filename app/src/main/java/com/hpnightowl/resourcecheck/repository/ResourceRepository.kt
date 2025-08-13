package com.hpnightowl.resourcecheck.repository

import android.content.Context
import android.content.res.Resources

data class ResourceResult(
    val value: Any?, val type: ResourceType, val found: Boolean
)

enum class ResourceType {
    BOOLEAN, INTEGER, STRING, DIMEN, COLOR, UNKNOWN
}

class ResourceRepository {

    fun getOverlayResource(
        context: Context, resourceName: String, resourceType: String
    ): ResourceResult {
        return try {
            val res: Resources = context.packageManager.getResourcesForApplication("android")
            val id = res.getIdentifier(resourceName, resourceType, "android")

            if (id != 0) {
                val value = when (resourceType) {
                    "bool" -> res.getBoolean(id)
                    "integer" -> res.getInteger(id)
                    "string" -> res.getString(id)
                    "dimen" -> "${res.getDimension(id)}px"
                    "color" -> {
                        String.format("#%08X", res.getColor(id, null))
                    }

                    else -> "Unsupported type"
                }
                ResourceResult(
                    value = value, type = mapStringToResourceType(resourceType), found = true
                )
            } else {
                ResourceResult(null, ResourceType.UNKNOWN, false)
            }
        } catch (e: Exception) {
            ResourceResult(null, ResourceType.UNKNOWN, false)
        }
    }

    private fun mapStringToResourceType(type: String): ResourceType {
        return when (type) {
            "bool" -> ResourceType.BOOLEAN
            "integer" -> ResourceType.INTEGER
            "string" -> ResourceType.STRING
            "dimen" -> ResourceType.DIMEN
            "color" -> ResourceType.COLOR
            else -> ResourceType.UNKNOWN
        }
    }

    fun getAllSupportedTypes(): List<String> = listOf("bool", "integer", "string", "dimen", "color")
}