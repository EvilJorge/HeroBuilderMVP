package com.example.android.herobuilder_mvp.PathFinder;

import android.content.Context;

/**
 * Holder interface that will contain all interfaces
 * responsible to maintain communication between
 * Model - View - Presenter layers.
 * Each layer implements its respective interface as follows:
 *
 *      View implements RequiredViewOps
 *      Presenter implements ProvidedPresenterOps and RequiredPresenterOps
 *      Model implements ProvidedModelOps
 *
 * Created by cpalomares on 1/4/2018.
 */

public interface MVP_PathFinder {
    /**
     * Required View methods made available to Presenter.
     * Responsible for showing data and receiving user
     * interactions.
     */
    interface RequiredViewOps{
        // View operations permitted to Presenter
        Context getAppContext();
        Context getActivityContext();

        // Ability Fragment View notifications
        void refreshAbilitiesPage();
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     *      View to Presenter
     */
    interface ProvidedPresenterOps{
        // Presenter operations permitted to View
        void setView(RequiredViewOps view);

        // Ops for Abilities Page
        void updateAbilityValue(int ability, int value);
        int getAbilityValue(int ability);
        int getAbilityModifier(int ability);
    }

    /**
     * Required Presenter Methods made available to Model.
     *      Model to Presenter
     */
    interface RequiredPresenterOps{
        // Presenter operations permitted to Model
        Context getAppContext();
        Context getActivityContext();
    }

    /**
     * Operations offered to Model to communicate with Presenter.
     * Handles all data business logic.
     */
    interface ProvidedModelOps {
        // Model operations permitted to Presenter
        String getCharacterName();
        void setCharacterName(String name);

        // Abilities operations
        int getAbilityValue(int ability);
        void setAbilityValue(int ability, int value);
        int getAbilityModifier(int ability);

        // Load Data from storage
        boolean loadData();
    }
}
