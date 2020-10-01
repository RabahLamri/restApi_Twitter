package myTweets;

import com.sun.media.sound.InvalidFormatException;
import data.DataReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProvider {

    @DataProvider
    public Object[][] getTestData() throws IOException, InvalidFormatException {
        Object data [][]= DataReader.fileReader3("TwitterData","../dataTest/TwitterData.xlsx");
        return data;
    }


    }

