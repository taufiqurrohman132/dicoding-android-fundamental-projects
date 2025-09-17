package com.example.myunittest

class CuboidModel {
    private var width = 0.0
    private var lenght = 0.0
    private var height = 0.0

    fun getVolume(): Double = width * lenght * height

    fun getSurfaceArea(): Double {
        val wl = width * lenght
        val wh = width * height
        val lh = lenght * height

        return 2 * (wl + lh + wh)
    }

    fun getCircumference(): Double = 4 * ( width + lenght + height)

    fun save(width: Double, length: Double, height: Double){
        this.width = width
        this.lenght = length
        this.height = height
    }


}