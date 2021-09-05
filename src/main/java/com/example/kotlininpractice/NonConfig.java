//package com.example.kotlininpractice;
//
//import java.util.HashMap;
//
//public class NonConfig {
//
//    // ComponentActivity
//    public ViewModelStore getViewModelStore() {
//        if (mViewModelStore == null) {
//            NonConfigurationInstances nc = (NonConfigurationInstances) getLastNonConfigurationInstance();
//            if (nc != null) mViewModelStore = nc.viewModelStore;
//            if (mViewModelStore == null) mViewModelStore = new ViewModelStore();
//        }
//        return mViewModelStore;
//    }
//
//    // Activity
//    public Object getLastNonConfigurationInstance() {
//        return mLastNonConfigurationInstances != null ? mLastNonConfigurationInstances.activity : null;
//    }
//
//
//    final void attach(NonConfigurationInstances lastNonConfigurationInstances){
//        mLastNonConfigurationInstances = lastNonConfigurationInstances;
//    }
//
//    NonConfigurationInstances retainNonConfigurationInstances() {
//        Object activity = onRetainNonConfigurationInstance();
//        HashMap<String, Object> children = onRetainNonConfigurationChildInstances();
//        FragmentManagerNonConfig fragments = mFragments.retainNestedNonConfig();
//
//        // We're already stopped but we've been asked to retain.
//        // Our fragments are taken care of but we need to mark the loaders for retention.
//        // In order to do this correctly we need to restart the loaders first before
//        // handing them off to the next activity.
//        mFragments.doLoaderStart();
//        mFragments.doLoaderStop(true);
//        ArrayMap<String, LoaderManager> loaders = mFragments.retainLoaderNonConfig();
//
//        if (activity == null && children == null && fragments == null && loaders == null
//                && mVoiceInteractor == null) {
//            return null;
//        }
//
//        NonConfigurationInstances nci = new NonConfigurationInstances();
//        nci.activity = activity;
//        nci.children = children;
//        nci.fragments = fragments;
//        nci.loaders = loaders;
//        if (mVoiceInteractor != null) {
//            mVoiceInteractor.retainInstance();
//            nci.voiceInteractor = mVoiceInteractor;
//        }
//        return nci;
//    }
//
//    public final Object onRetainNonConfigurationInstance() {
//        Object custom = onRetainCustomNonConfigurationInstance();
//
//        ViewModelStore viewModelStore = mViewModelStore;
//        if (viewModelStore == null) {
//            // No one called getViewModelStore(), so see if there was an existing
//            // ViewModelStore from our last NonConfigurationInstance
//            NonConfigurationInstances nc =
//                    (NonConfigurationInstances) getLastNonConfigurationInstance();
//            if (nc != null) {
//                viewModelStore = nc.viewModelStore;
//            }
//        }
//
//        if (viewModelStore == null && custom == null) {
//            return null;
//        }
//
//        NonConfigurationInstances nci = new NonConfigurationInstances();
//        nci.custom = custom;
//        nci.viewModelStore = viewModelStore;
//        return nci;
//    }
//
//}
