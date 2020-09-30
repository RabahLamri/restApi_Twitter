package myTweetTest;

import com.github.scribejava.apis.TwitterApi;
import io.restassured.response.ValidatableResponse;
import myTweets.MyTweetApi;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.util.UUID;

public class MyTweetApiTest {
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
    public void WeatherTest(){
    myTweetApi.weather();

    }@Test
    public void forecastTest() {
        myTweetApi.forecast();


    }


    }
