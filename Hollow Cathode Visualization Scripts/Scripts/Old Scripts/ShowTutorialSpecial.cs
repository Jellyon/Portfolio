using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ShowTutorialSpecial : MonoBehaviour
{
    /*
     * Keeping an UI inside of a Empty Parent object messes with thier pivots.
     * Pivots are used so the UI is always in the same place no matter the resolution.
     * This code just activates the Button Image and tutorial part when the parent gameobject
     * is active, and closes the button and tutorialpart when the parent is in active.
     * 
     * The current position works formost resolutions around 1080p
     * others make it more condensed or stretched.
     */
    public GameObject ParentPart;
    public GameObject FakeButton;
    public GameObject TutorialPart;
    void Start()
    {
        ParentPart.SetActive(false);
    }

    // Update is called once per frame
    void Update()
    {
        if(ParentPart.activeSelf == true)
        {
            FakeButton.SetActive(true);
            TutorialPart.SetActive(true);
        }
        else if (ParentPart.activeSelf == false)
        {
            FakeButton.SetActive(false);
            TutorialPart.SetActive(false);
        }
    }
}
