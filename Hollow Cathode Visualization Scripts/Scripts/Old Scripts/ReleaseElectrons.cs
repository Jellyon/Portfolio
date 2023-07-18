using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ReleaseElectrons : MonoBehaviour
{
    [SerializeField]
    public GameObject ElectronPrefab; //Make sure to put electron prefabe in the slot in Unity
    [Range(0, 1)] public float ElectronSize; //Makes size range scale as a slider bar
    public Material mateerial;
    public int ElectronCount = 0;
    public int ElectronCountMax;

    private void Start()
    {
        gameObject.GetComponent<Renderer>();
    }
    void Update()
    {
        try
        {
            if (gameObject.GetComponent<Renderer>().material == mateerial)
            {
                Debug.Log("Its Red");
                if (ElectronCount < ElectronCountMax)
                {
                    SpawnElectrons();
                }
            }
        }
        catch (System.Exception) { Debug.Log("SpawnElectrons Exception"); }


        if (Input.GetKeyDown(KeyCode.Return)) // Press enter to spawn an electron
        {

            SpawnElectrons();

        }
    }

    void SpawnElectrons()
    {
        try
        {
            /*
             * To get the electron position we take the gameObject (Cathode Insert Object) position
             * which should be the center of the cathode insert, add that with a new random position inside of the cathode
             * 
             * The cathode insert is about 13 units tall with a radius of 1 unit.
             */
            Vector3 ElectronPos = gameObject.GetComponent<Renderer>().transform.position + new Vector3(Random.Range(-1, 0), Random.Range(-6.7f, 6.7f), Random.Range(-1, 1));
            
            //Adds a new electron into the canvas
            GameObject obj = Instantiate(ElectronPrefab, ElectronPos, Quaternion.identity);
            int direction = Random.Range(0, 1); // determines if the force will push it from the x or z axis.
            if (direction == 0) { obj.GetComponent<Rigidbody>().AddForce(0, Random.Range(-150.0f, 150.0f), 200, ForceMode.Acceleration); }
            if (direction == 1) { obj.GetComponent<Rigidbody>().AddForce(200, Random.Range(-150.0f, 150.0f), 0, ForceMode.Acceleration); }

            ElectronCount++;
        }
        catch(System.Exception) { Debug.Log("SpawnElectrons Exception"); }

    }
}
