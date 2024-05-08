package com.example.ch18_joyce

data class myJsonItems(val districtName: String, val issueDate: String, val issueTime: String, val issueGbn: String)
data class myJsonBody(val items: MutableList<myJsonItems>)
data class myJsonResponse(val body: myJsonBody)
data class JsonResponse(val response: myJsonResponse)
