package it.iacovelli.funnyconverterbe.service

interface ConverterService {

    fun convert(from: String, to: String, value: Double): Double

}