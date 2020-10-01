package myTweetTest;

import base.RestAPI;
import myTweets.*;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class MyTweetApiTest extends DataProvider {
    private MyTweetApi myTweetApi;

    @BeforeClass
    public void setUpTweetAPI(){
        this.myTweetApi =new MyTweetApi();
    }

    @Test(enabled = false)
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="Hello, This is my first tweet from intelliJ with hassan"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.myTweetApi.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = false)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
        // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="Hello, This is my first tweet from intelliJ";
        ValidatableResponse response= this.myTweetApi.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
        response= this.myTweetApi.createTweet(tweet);
        // Verify that the tweet was unsuccessful
        response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }

    @Test(enabled = true)
    public void testDelete(){
        String tweet="Hello, This is my first tweet from intelliJ with hassan";
        ValidatableResponse response=this.myTweetApi.deleteTweet(1307388188433027072l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow1(){
        String tweet="Azul Felawen  "+ UUID.randomUUID().toString();
        ValidatableResponse response=this.myTweetApi.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        response=this.myTweetApi.createTweet(tweet);
        response.statusCode(403);
        String expectedMessage="Status is a duplicate.";
        String actualMessage=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void getMyUserTimeTweetTest(){

        ValidatableResponse response=this.myTweetApi.getMyUserTimeTweet(1307288019985223692l);
        String actualMessage=response.extract().body().path("errors[0].message");
        response.statusCode(200);
        System.out.println(actualMessage);
    }

    @Test
    public void WeatherTest(){
    myTweetApi.weather();

    }
    @Test
    public void forecastTest() {
        myTweetApi.forecast();
    }
////=======================



        @Test (enabled = true)
        public void testUserTimeTweetSuccessfully(){
            String tweet="This is a BootCamp Tweet";
            ValidatableResponse response= this.myTweetApi.getUserTimeTweet(tweet);
            System.out.println(response);
        }



        @Test (enabled = true)
        public void testUserPlaceNearTweetSuccessfully(){
            String atUsername="@AribElhacen";
            ValidatableResponse response= this.myTweetApi.getPlacesNear(atUsername);
            System.out.println(response);
        }


        @Test (enabled = false)
        public void testUserCanTweetAnImageSuccessfully(){
// user send an image tweet
            String tweet="../Users/elhacenarib/Desktop/image.png";
            ValidatableResponse response= this.myTweetApi.createImageTweet(tweet);
// Verify that the tweet was successful
            response.statusCode(200);
            String actualTweet= response.extract().body().path("image");
            Assert.assertEquals(tweet,actualTweet);
        }




    }
