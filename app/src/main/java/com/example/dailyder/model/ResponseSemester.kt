package com.example.dailyder.model

import java.io.Serializable

data class ResponseSemester(
	val data: List<DataItemSemester>,
	val sukses: Boolean,
	val message: String
)

data class DataItemSemester(
	val semester: String,
	val id: String
): Serializable
