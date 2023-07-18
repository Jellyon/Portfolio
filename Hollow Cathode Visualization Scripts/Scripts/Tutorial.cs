using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Tutorial : MonoBehaviour
{

    /* Controls the Ui within the tutorial Panel.
     * Its mostly the previous and next button controls.
     * 
     */
    public GameObject tutorialPanel;        // For turning the tutorial panel onor off
    public GameObject[] tutorialParts;      // Add an element for an additional part to the tutorial.
    public GameObject tutorialPos;          //For when its time to start the tutorial this is the position for the cam angle.
    private int index;

    void Start()
    {
        tutorialPos.SetActive(false);
        tutorialPanel.SetActive(false); //deactivate panel at play start
        foreach (GameObject part in tutorialParts)
        {
            part.SetActive(false); // deactivate all parts at play start
        }
    }

    // Update is called once per frame
    void Update()
    {
        tutorialParts[index].SetActive(true);   //When on that index that part of the tutorial shows up
    }

    public void startTutorial()
    {
        tutorialPanel.SetActive(true);
    }

    // deactivates current tutorialPart and anctivates a previous part.
    public void prevButton()
    {
        tutorialParts[index].SetActive(false); 
        if (index == 0)
        { } //Does nothing if it is the first tutorialPart.
        else
        { index--; }
    }

    // deactivates current tutorialPart and anctivates the next part.
    public void nextButton()
    {
        tutorialParts[index].SetActive(false);
        if (index == (tutorialParts.Length-1) ) // When it reaches the end it deactivates the tutorialPanel
        {
            tutorialPos.SetActive(false); // Deactivates the the secondary cam so the main can pick up where it left off.
            tutorialPanel.SetActive(false); 
        }
        else
        { index++; }
    }

    public void tutorialpos()   // Makes the cam go to the tutorial position
    {
        tutorialPos.SetActive(true);
    }
}
