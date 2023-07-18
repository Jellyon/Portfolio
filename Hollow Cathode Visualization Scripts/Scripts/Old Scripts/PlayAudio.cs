using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayAudio : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject objectCanvas;
    public AudioClip[] audioFiles;
    public Material[] material;
    public GameObject camBtn;
    private AudioSource audioSource;
    private bool showObjCanvas = true;
    [HideInInspector] public bool stopGlow = false;
    public starttour st;
    /*
     * 
     * 0 is Insert
     * 1 is Heater
     * 2 is Keeper
     * 
     */
    private void Start()
    {
        audioSource = GetComponent<AudioSource>();
        objectCanvas.SetActive(true);
        stopGlow = false;
        foreach(Material mat in material)
        {
            mat.DisableKeyword("_EMISSION");
        }
    }
    private void Update()
    {
        if(audioSource.isPlaying)
        {
            objectCanvas.SetActive(false);
            stopGlow = false;
        }
        else
        {
            if(st.tourNum==0 && !st.moving){camBtn.SetActive(true);}
            objectCanvas.SetActive(true);
            stopGlow = true;
            for (int i = 0; i < material.Length; i++)
                material[i].DisableKeyword("_EMISSION");
        }

        // For when outside scripts want to turn it on or off.
        if(!showObjCanvas) { objectCanvas.SetActive(false); }
    }

    public void playaudio(){
       
    }

    //Used to stop objects from glowing
    
    

    public void PlayInsertAudio()
    {
        audioSource.PlayOneShot(audioFiles[0]);
        material[0].EnableKeyword("_EMISSION");
        //audioSource.PlayOneShot(audioFiles[0], volume scale);

    }

    public void PlayHeaterAudio()
    {
        audioSource.PlayOneShot(audioFiles[1]);
        material[1].EnableKeyword("_EMISSION");
    }

    public void PlayKeeperAudio()
    {
        audioSource.PlayOneShot(audioFiles[2]);
        material[2].EnableKeyword("_EMISSION");

    }
    public void HideObjCanvas()
    {
        showObjCanvas = false;
    }

    public void ShowObjCanvas()
    {
        showObjCanvas = true;
        
    }
}
