using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UI_Image_Handler : MonoBehaviour
{

    // Used in the main menu
    private Image img;
    [Range(0, 1)] public float opacity;
    Color imgColor;
    void Start()
    {
        img = GetComponent<Image>();

        img.color = new Color(255, 255, 255, 0.75f);


    }

    // Update is called once per frame
    void Update()
    {
       img.color = new Color(255, 255, 255, opacity);
    }



}
