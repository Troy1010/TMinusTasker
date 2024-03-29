package com.tminus1010.tminustasker.environment.adapter

import com.squareup.moshi.*
import com.tminus1010.tminustasker.all_layers.extensions.writeArray
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object PairAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (type !is ParameterizedType) {
            return null
        }
        if (Pair::class.java != type.rawType) {
            return null
        }

        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val listAdapter = moshi.adapter<List<String>>(listType)

        return PairAdapter(moshi.adapter(type.actualTypeArguments[0]), moshi.adapter(type.actualTypeArguments[1]), listAdapter)
    }

    private class PairAdapter(
        private val firstAdapter: JsonAdapter<Any>,
        private val secondAdapter: JsonAdapter<Any>,
        private val listAdapter: JsonAdapter<List<String>>,
    ) : JsonAdapter<Pair<Any, Any>>() {
        override fun fromJson(reader: JsonReader): Pair<Any, Any>? {
            val list = listAdapter.fromJson(reader) ?: return null

            require(list.size == 2) {
                "pair with more or less than two elements: $list"
            }
            val first = firstAdapter.fromJsonValue(list[0])!!
            val second = secondAdapter.fromJsonValue(list[1])!!
            return first to second
        }

        override fun toJson(writer: JsonWriter, value: Pair<Any, Any>?) {
            if (value != null)
                writer.writeArray {
                    firstAdapter.toJson(writer, value.first)
                    secondAdapter.toJson(writer, value.second)
                }
        }
    }
}