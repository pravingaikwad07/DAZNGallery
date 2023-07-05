package com.pravin.dazngallery.utils

import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * Function to convert the string array in json format to List of type T
 * Params: jsonArray - String in jsonarray format
 */
fun <T> getList(jsonArray: String?, clazz: Class<T>?): List<T>? {
    val typeOfT: Type = TypeToken.getParameterized(MutableList::class.java, clazz).type
    return Gson().fromJson(jsonArray, typeOfT)
}