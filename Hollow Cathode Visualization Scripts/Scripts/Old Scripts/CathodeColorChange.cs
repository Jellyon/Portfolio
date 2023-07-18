using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CathodeColorChange : MonoBehaviour
{
    [SerializeField]
    [Range(0, 1)] public float lerpTime; //Because our lerp points will be colors instead of numbers we need a range so we can define the current value/position, also determines speed of change
    public GameObject CathodeInsertPrefab; // Place the GameObject or Cathode object inside to have its color changed, make sure the object already has a meshrender and material
    public GameObject ElectronPrefab;

    private Renderer CathodeInsertRender;
    [Range(0, 1)] public float ElectronSize = 0.1f; //Makes size range scale as a slider bar
    public int ElectronCount;




    private bool COC = false;

    private void Start()
    {
        lerpTime = 0.3f;
        CathodeInsertRender = CathodeInsertPrefab.GetComponent<Renderer>();
        gameObject.GetComponent<Button>().onClick.AddListener(colorObjectChange); //So it actually works when the button is clicked
    }
    private void Update()
    {
        if (COC == true)
        {
            //Lerp interpolates between 2 points. The method has 3 parameters, pointA (Start), pointB(End), value(current position between the two moints)
            //After button is clicked this makes sure the color gradually changes to red.
            CathodeInsertRender.material.color = Color.Lerp(CathodeInsertRender.material.color, Color.red, lerpTime * Time.deltaTime);
           
        }

        int ElectronCount = 0;
        if(ElectronCount < 10)
        {
            if (CathodeInsertRender.material.color == Color.red)
            {
                SpawnElectrons();
                ElectronCount++;
            }
        }
        if(CathodeInsertRender.material.color == Color.red)
        {
            
        }
        if (Input.GetKeyDown(KeyCode.Return)) // Press enter to spawn an electron
        {

            SpawnElectrons();

        }

    }

    //This function activates when button is clicked
    void colorObjectChange() 
    {
        COC = true;

    }
    void SpawnElectrons( )
    {
        for(int i = 0; i < ElectronCount; i++)
        {
            Vector3 ElectronPos = CathodeInsertPrefab.transform.position + new Vector3(Random.Range(-1, 0), Random.Range(-6.7f, 6.7f), Random.Range(-1 , 1));
            GameObject obj = Instantiate(ElectronPrefab, ElectronPos, Random.rotation);
            int direction = Random.Range(0, 1);
            if(direction == 0){ obj.GetComponent<Rigidbody>().AddForce(0, Random.Range(-150.0f, 150.0f), 200, ForceMode.Acceleration); }
            if (direction == 1) { obj.GetComponent<Rigidbody>().AddForce(200, Random.Range(-150.0f, 150.0f), 0, ForceMode.Acceleration); }

        }
       
    }
}
