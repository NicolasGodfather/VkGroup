package com.adnroid.vkgroup.common.manager;


import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.fragment.BaseFragment;

import java.util.Stack;

public class MyFragmentManager {

    public static final int EMPTY_FRAGMENT_STACK_SIZE = 1; // for save only 1 fragment in stack, for accidentally not remove our fragment
    private Stack<BaseFragment> fragmentStack = new Stack<>();
    private BaseFragment currentFragment;

    // set root fragment, here display main content of screen(title, float button)
    public void setFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        // check for exit this fragment in stack
        if (activity != null && !activity.isFinishing() && isAlreadyContains(fragment)) {
            FragmentTransaction transaction = createAddTransaction(activity, fragment, false);
            transaction.replace(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, false);
        }
    }

    // add fragment over the root, in them will open item of menu navigation
    public void addFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && isAlreadyContains(fragment)) {
            FragmentTransaction transaction = createAddTransaction(activity, fragment, false);
            transaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, true);
        }
    }

    private boolean removeCurrentFragment(BaseActivity activity) {
         return removeFragment(activity, currentFragment);
    }

    public boolean removeFragment(BaseActivity activity, BaseFragment fragment) {

        boolean canRemove = fragment != null && fragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;

        if (canRemove) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentStack.pop(); // remove last fragment from list of stack
            currentFragment = fragmentStack.lastElement();

            transaction.remove(fragment);
            commitTransaction(activity, transaction);
        }

        return canRemove;
    }

    private FragmentTransaction createAddTransaction(BaseActivity activity,
                                                     BaseFragment fragment,
                                                     boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        return fragmentTransaction;
    }

    private void commitAddTransaction(BaseActivity activity, BaseFragment fragment,
                                      FragmentTransaction transaction, boolean addToBackStack) {
        if (transaction != null) {
            currentFragment = fragment;

            if (!addToBackStack) { // if fragment not added to stack - clear stack
                fragmentStack = new Stack<>();
            }

            fragmentStack.add(fragment);
        }

        commitTransaction(activity, transaction);
    }

    // for commit any transaction anyway it added or removed
    private void commitTransaction(BaseActivity activity, FragmentTransaction transaction) {
        transaction.commit();

        activity.fragmentOnScreen(currentFragment);
    }

    public boolean isAlreadyContains(BaseFragment baseFragment) {
        if (baseFragment == null) {
            return false;
        }

        return currentFragment != null
                && currentFragment.getClass().getName().equals(baseFragment.getClass().getName());
    }

}
