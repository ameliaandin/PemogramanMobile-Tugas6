package com.utama.aplikasiloginsederhana

data class MedicalRecord(
    val id: Int,
    val patientName: String,
    val examination: String,
    val date: String,
    val result: String
)
