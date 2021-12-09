package com.dipesh.onlinegadgetsstore

import com.dipesh.onlinegadgetsstore.api.ServiceBuilder
import com.dipesh.onlinegadgetsstore.entity.User
import com.dipesh.onlinegadgetsstore.repository.CartRepository
import com.dipesh.onlinegadgetsstore.repository.UserRepository
import com.dipesh.onlinegadgetsstore.repository.WishListRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {
    private lateinit var userRepository:UserRepository
    private lateinit var cartRepository:CartRepository
    private lateinit var wishRepository:WishListRepository

    @Test
    fun loginTest() = runBlocking {
        userRepository= UserRepository()
        val response =userRepository.checkUser("dipesh@gmail.com","lordrings")
        val expectedResult= true
        val actualResult= response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun registerTest()= runBlocking {
        userRepository= UserRepository()
        val user= User(firstName = "Anush",lastName = "Gajurel",username = "Anush Gajurel", email = "anush@gmail.com", password = "appleapple" )
        val response = userRepository.registerUser(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun addToCart()= runBlocking {
        userRepository= UserRepository()
        cartRepository= CartRepository()
        ServiceBuilder.token= "Bearer "  + userRepository.checkUser("dipesh@gmail.com","lordrings").token
        val response = cartRepository.addToCart( "60670a6da46b4a23304b49dd")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun deleteCartItems()= runBlocking {
        userRepository= UserRepository()
        cartRepository= CartRepository()
        ServiceBuilder.token= "Bearer "  + userRepository.checkUser("pukar@gmail.com","lordrings").token
        val response =cartRepository.deleteCart("607949636b2517756c")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun addToFavourites()= runBlocking {
        userRepository = UserRepository()
        wishRepository = WishListRepository()
        ServiceBuilder.token = "Bearer " + userRepository.checkUser("dipesh@gmail.com", "lordrings").token
        val response = wishRepository.addtofav("60670a6da46b4a23304bd")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }


}



