package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {

    public boolean isIssueOpen(int issueId){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        String json =  RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issuesParsed = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
        Issue issue = issuesParsed.iterator().next();
        return !issue.getState_name().equals("Resolved") && !issue.getState_name().equals("Closed");
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
