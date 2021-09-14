package com.nasa.nasapicturesviewer;

import android.content.Context;

import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;
import com.nasa.nasapicturesviewer.data.NasaPicturesDataContract;
import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.Detail.NasaPictureDetailActivity;
import com.nasa.nasapicturesviewer.view.List.NasaPictureListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NasaPictureTests {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.nasa.nasapicturesviewer", appContext.getPackageName());
    }

    /**
     * Check if data is being parsed from the json file and being returned
     */
    @Test
    public void checkIfNasaPicturesLoads() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        NasaPicturesDataContract.NasaPicturesLoadingFinishedListener loadingFinishedListener = new NasaPicturesDataContract.NasaPicturesLoadingFinishedListener() {
            @Override
            public void onCompletion(List<NasaPicture> nasaPictureList) {
                int empty = 0;
                assertTrue(nasaPictureList.size() > empty);
            }

            @Override
            public void onFailure(String error) {
                assert false;
            }
        };

        GetNasaPictureDataContractImpl nasaPictureList = new GetNasaPictureDataContractImpl(appContext);
        nasaPictureList.getPicturesForNasaGallery(loadingFinishedListener);
    }

    @Test
    public void checkIfNasaPicturesListIsSorted() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        GetNasaPictureDataContractImpl getNasaPictureDataContract = new GetNasaPictureDataContractImpl(appContext);

        NasaPicturesDataContract.NasaPicturesLoadingFinishedListener loadingFinishedListener = new NasaPicturesDataContract.NasaPicturesLoadingFinishedListener() {
            @Override
            public void onCompletion(List<NasaPicture> nasaPictureList) {
                List<NasaPicture> sortedNasaPicturesList = getNasaPictureDataContract.sortNasaPicturesByMostRecentDate(nasaPictureList);
                if (sortedNasaPicturesList.size() > 0) {
                    NasaPicture prev = sortedNasaPicturesList.get(0);
                    nasaPictureList.remove(0);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

                    for (NasaPicture picture : sortedNasaPicturesList) {
                        LocalDate prevPictureDate = LocalDate.parse(prev.getDate(), formatter);
                        LocalDate currentPictureDate = LocalDate.parse(picture.getDate(), formatter);
                        if (prevPictureDate.isBefore(currentPictureDate)) {
                            assert false;
                            return;
                        }
                        prev = picture;
                    }
                    assert true;
                }
            }

            @Override
            public void onFailure(String error) {
                assert false;
            }
        };

        getNasaPictureDataContract.getPicturesForNasaGallery(loadingFinishedListener);

    }

    @Rule
    public ActivityScenarioRule<NasaPictureListActivity> activityListRule
            = new ActivityScenarioRule<>(NasaPictureListActivity.class);

    /**
     * Check if detail picture activity is opening detail activity
     */
    @Test
    public void checkIfNasaPicturesClickIsFunctioning() {
        onView(withId(R.id.recycler_view)).perform(click());

        intended(hasComponent(NasaPictureDetailActivity.class.getName()));
    }


    @Rule
    public ActivityScenarioRule<NasaPictureDetailActivity> activityDetailRule
            = new ActivityScenarioRule<>(NasaPictureDetailActivity.class);
    /**
     * Check if detail picture activity is swiping
     */
    @Test
    public void checkIfViewPagerIsWorking() {
        onView(withId(R.id.nasa_pictures_viewpager)).perform(ViewActions.swipeRight());
        intended(hasComponent(NasaPictureDetailActivity.class.getName()));
    }

}