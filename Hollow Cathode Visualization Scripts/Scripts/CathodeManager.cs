using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CathodeManager: MonoBehaviour
{
    [SerializeField]
    [Range(0, 100)] public float lerpDuration; //Because our lerp points will be colors instead of numbers we need a range so we can define the current value/position, also determines speed of change
    
    
    public GameObject ElectronPrefab;   //Prefab for the electrons
    public GameObject Emitter_Model;    //  The Emitter object
    public GameObject Heaters_Model;    //  The Parent object that contains all of the heaters
    public GameObject Keeper_Model;
    public Material heatedMaterial;     //  The material with the color the Emitters/Heaters change into
    public Material EmitterResetMat;
    public Material HeaterResetMat;
    public GameObject openModel;        //  The open Model Button
    public GameObject activateModel;    //  The Activate Button
    public GameObject ResetModel;       //  The reset model button
    public GameObject objectCanvas;     //  The object Canvas
    public int ElectronMax;             // The max amount of electrons that can be produced


    private PlayAudio PA;
    private List<GameObject> ElectronList;
    private Renderer[] CathodeHeaters;
    private Renderer RendEmitter;
    private Renderer RendKeepers;
    private float timeElasped;
    private int ElectronCount = 0;      // Keeps track of the number of electrons
    private bool Heaters = false;
    private bool Emmitters = false, EmittersOn = false;
    [HideInInspector]
    public bool InsertHeat = false;

    //Inner Cathode Cutscene variables
    public AudioClip[] audioFiles;
    public GameObject camBtn;
    private AudioSource audioSource;
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

       
        CathodeHeaters = Heaters_Model.GetComponentsInChildren<Renderer>();
        RendEmitter = Emitter_Model.GetComponent<Renderer>();
        ElectronList = new List<GameObject>();
        RendKeepers = Keeper_Model.GetComponent<Renderer>();

        HeaterResetMat.Lerp(HeaterResetMat, CathodeHeaters[3].material, 1);
        EmitterResetMat.Lerp(EmitterResetMat, RendEmitter.material, 1);
        openModel.SetActive(true);
        activateModel.SetActive(false);

        //Inner Cathode Cutscene 
        audioSource = GetComponent<AudioSource>();
        objectCanvas.SetActive(true);

    }
    private void Update()
    {
        if (Heaters == true)
        {
            
            //After button is clicked this makes sure the color gradually changes to red
            StartCoroutine(HeatersColorChange());
            Heaters = false;
        }

        
        if(Emmitters == true)
        {
            StartCoroutine(EmittersColorChange() );
            Emmitters = false;
        }
        if (EmittersOn == true && ElectronList.Count < ElectronMax)
        {
            SpawnElectrons();
            
        }

        


        //Inner Cathode Cutscene
        if (audioSource.isPlaying)
        {
            objectCanvas.SetActive(false);
            stopGlow = false;
        }
        else
        {
            if (st.tourNum == 0 && !st.moving) { camBtn.SetActive(true); }
            objectCanvas.SetActive(true);
            stopGlow = true;
            StopGlowing();
        }

       
    }

    
    // Used to reset the cathode
    public void ResetCathode()
    {
        Heaters = false;
        Emmitters = false;
        EmittersOn = false;
        foreach (Renderer child in CathodeHeaters)
        {
            // Change the Emitters and Heaters material back to the initial state.
            child.material.Lerp(child.material, HeaterResetMat, 1);
        }
        RendEmitter.material.Lerp(RendEmitter.material, EmitterResetMat, 1);

        //Destroy all electrons
        foreach (GameObject obj in ElectronList)
        {
            Destroy(obj);
        }
        ElectronList.Clear(); // Clear the list
        ResetModel.SetActive(false); // hide Resset button
        activateModel.SetActive(true); // show activate cathode button

    }

    public void InsertGlow()
    {
        audioSource.PlayOneShot(audioFiles[0]);
        RendEmitter.material.EnableKeyword("_EMISSION");

    }
    private void StopGlowing()
    {
        RendEmitter.material.DisableKeyword("_EMISSION");
        RendKeepers.material.DisableKeyword("_EMISSION");
        foreach (Renderer child in CathodeHeaters)
        {
            child.material.DisableKeyword("_EMISSION");
        }
    }

    public void HeaterGlows()
    {
        audioSource.PlayOneShot(audioFiles[1]);
        foreach (Renderer child in CathodeHeaters)
        {
            
            //Turns on the emission which allows it to look like its glowing
            child.material.EnableKeyword("_EMISSION");
        }

    }

    public void KeeperGlow()
    {
        audioSource.PlayOneShot(audioFiles[2]);
        RendKeepers.material.EnableKeyword("_EMISSION");

    }
    

   
    // Makes the Open Model button dissapear and Activate Button appear on click
    public void Open_Model_Hide()
    {
        openModel.SetActive(false);
        activateModel.SetActive(true);
    }
        //Make the activate button dissappear
    private void Activate_Model()
    {
        activateModel.SetActive(false);
    }

        //This function activates when button is clicked
    public void colorObjectChange()
    {
        Heaters = true;
        Activate_Model();
    }

    private void SpawnElectrons()
    {
        try
        {
            /*
            * To get the electron position we take the Cathodes Insert/Emitters Renderers position.
            * which should be the center of the cathode insert, add that with a new random position inside of the cathode
            * 
            * The cathode insert is about 13 units tall with a radius of 1 unit.
            */
            Vector3 ElectronPos = RendEmitter.transform.position + new Vector3(Random.Range(-0.7f, 0.7f), Random.Range(-6.5f, 6.5f), Random.Range(-0.7f, 0.7f));
            
            //Adds a new electron into the canvas
            GameObject obj = Instantiate(ElectronPrefab, ElectronPos, Quaternion.identity);
            ElectronList.Add((GameObject)obj);
            int direction = Random.Range(0, 1);// determines if the force will push it from the x or z axis.
            if (direction == 0) { obj.GetComponent<Rigidbody>().AddForce(0, Random.Range(-150.0f, 150.0f), 200, ForceMode.Acceleration); }
            if (direction == 1) { obj.GetComponent<Rigidbody>().AddForce(200, Random.Range(-150.0f, 150.0f), 0, ForceMode.Acceleration); }

            
            ElectronCount++;
        }
        catch (System.Exception) { Debug.Log("SpawnElectrons Exception"); }

    }

    //It hides the ObjectCanvas
    public void HideObjCanvas()
    {
        objectCanvas.SetActive(false);
       
        Debug.Log("Hiding Object Canvas");
    }

    public void ShowObjCanvas()
    {
        objectCanvas.SetActive(true);

    }


    IEnumerator HeatersColorChange()
    {
        while (timeElasped < lerpDuration)
        {
            foreach (Renderer child in CathodeHeaters)
            {
                //Lerp interpolates between 2 points. The method has 3 parameters, pointA (Start), pointB(End), value(current position between the two moints)
                child.material.Lerp(child.material, heatedMaterial, timeElasped / (lerpDuration * 100));

            }
            timeElasped += Time.deltaTime;
            yield return null;
        }
        // finishes the remaining bit of color.
        foreach (Renderer child in CathodeHeaters)
        {
            child.material = heatedMaterial;

        }
        Emmitters = true;
        timeElasped = 0.0f;
        
       
    }

    IEnumerator EmittersColorChange()
    {
        
        timeElasped = 0.0f;
        while (timeElasped < lerpDuration)
        {
            //Lerp interpolates between 2 points. The method has 3 parameters, pointA (Start), pointB(End), value(current position between the two moints)
            RendEmitter.material.Lerp(RendEmitter.material, heatedMaterial, timeElasped / (lerpDuration * 50f));

            timeElasped += Time.deltaTime;
            yield return null;
        }
        EmittersOn = true;
        ResetModel.SetActive(true);

    }

}
