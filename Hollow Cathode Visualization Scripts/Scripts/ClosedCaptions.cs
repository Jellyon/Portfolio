using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ClosedCaptions : MonoBehaviour
{
    private Animator anim;

    void Start()
    {

        anim = GetComponent<Animator>();
        anim.Play("Intro", -1);
    }

    public void OnClick(string clickedObject)
    {
        if (MenuButtons.CC_Enabled)
        {
            anim.Play(clickedObject, -1);
        }
    }


}
