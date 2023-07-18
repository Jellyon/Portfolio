using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HIdeModel : MonoBehaviour
{
    //public GameObject DupModel;
    
    // Update is called once per frame
    void Update()
    {
        
        if (Input.GetKeyDown(KeyCode.H))
        {
            for (int i = 0; i < gameObject.transform.childCount; i++)
            {
                gameObject.transform.GetChild(i).gameObject.GetComponent<Renderer>().enabled = false;

            }
        }

        if (Input.GetKeyDown(KeyCode.G))
        {
            for (int i = 0; i < gameObject.transform.childCount; i++)
            {
                gameObject.transform.GetChild(i).gameObject.GetComponent<Renderer>().enabled = true;
                

            }
        }
    }
}
