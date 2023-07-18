using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Audio;
using UnityEngine.UI;

public class VolumeSlider : MonoBehaviour
{
    public AudioMixer mixer;
    
    void Start(){
        mixer.SetFloat("Music", Mathf.Log10(.25f) * 20);
    }

    public void Setlevel(float slider){
        mixer.SetFloat("Music", Mathf.Log10(slider) * 20);
    }
}
