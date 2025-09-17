package com.example.myunittest

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var cuboidModel: CuboidModel

    private val dummyL = 12.0
    private val dummyW = 7.0
    private val dummyH = 6.0

    private val dummyVolume = 504.0
    private val dummyCircumference = 100.0
    private val dummySurafaceArea = 396.0

    @Before
    fun before(){
        cuboidModel = mock(CuboidModel::class.java)
        viewModel = MainViewModel(cuboidModel)
    }

    @Test
    fun testCircumference(){
        cuboidModel = CuboidModel()
        viewModel = MainViewModel(cuboidModel)
        viewModel.save(dummyW, dummyL, dummyH)
        val circume = viewModel.getCircumference()
        assertEquals(dummyCircumference,circume,0.0001)
    }

    @Test
    fun testSurfaceArea(){
        cuboidModel = CuboidModel()
        viewModel = MainViewModel(cuboidModel)
        viewModel.save(dummyW, dummyL, dummyH)
        val surface = viewModel.getSurfaceArea()
        assertEquals(dummySurafaceArea,surface,0.0001)

    }

    @Test
    fun tesVolume(){
        cuboidModel = CuboidModel()
        viewModel = MainViewModel(cuboidModel)
        viewModel.save(dummyW, dummyL, dummyH)
        val volume = viewModel.getVolume()
        assertEquals(dummyVolume,volume,0.0001)
    }

    // pengujain menggunakan model mock
    @Test
    fun testMockVolume(){
        `when` (viewModel.getVolume()).thenReturn(dummyVolume)
        val volume = viewModel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(dummyVolume,volume,0.0001)
    }

    @Test
    fun testMockCircumference(){
        `when` (viewModel.getCircumference()).thenReturn(dummyCircumference)
        val circum = viewModel.getCircumference()
        verify(cuboidModel).getCircumference()
        assertEquals(dummyCircumference,circum,0.0001)
    }

    @Test
    fun testMockSurfaceArea(){
        `when` (viewModel.getSurfaceArea()).thenReturn(dummySurafaceArea)
        val surface = viewModel.getSurfaceArea()
        verify(cuboidModel).getSurfaceArea()
        assertEquals(dummySurafaceArea,surface,0.0001)
    }

    @Test
    fun getCircumference() {
    }

    @Test
    fun getSurfaceArea() {
    }

    @Test
    fun getVolume() {
    }

    @Test
    fun save() {
    }
}