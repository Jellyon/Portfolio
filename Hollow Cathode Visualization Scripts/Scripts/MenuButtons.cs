using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MenuButtons : MonoBehaviour
{
    public GameObject[] menuList;
    public static bool CC_Enabled = true;
    

    public void StartVisualization()
    {
        SceneManager.LoadScene("Activate Cathode");
        
    }

    public void Credits()
    {
        /*
         * 0 is the Start Canvas
         * 1 is the credits Canvas
         *
         */
        for (int i = 0; i < menuList.Length; i++)
        {
            if (i == 1)
            {
                menuList[i].SetActive(true);
            }
            else
            {
                menuList[i].SetActive(false);
            }

        }

    }


    public void BackButton()
    {
        /*
         * 0 is the Start Canvas
         * 1 is the credits Canvas
         *
         */
        for (int i = 0; i < menuList.Length; i++)
        {
            if (i == 0) // turns on start canvas
            {
                menuList[i].SetActive(true);
            }
            else  // turn off options canvas
            {
                menuList[i].SetActive(false);
            }

        }
    }


}
