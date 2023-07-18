using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraControls : MonoBehaviour
{
    [SerializeField]
    public float camSpeed = 2.0f;
    public float moveSpeed = 3.0f;
    public float maxTurn = 90.0f;
    public float minTurn = -90.0f;
    public GameObject[] CamList;
    private int camCount;

    public float rotate;

   

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButton(0)) MouseView();
        KeyMove();

        if (Input.GetKeyDown (KeyCode.Alpha1)) 
        {
            if (camCount == 0)
            {
                camCount = CamList.Length - 1;

            }
            else { camCount--; }
            gameObject.transform.position = CamList[camCount].transform.position;
            gameObject.transform.rotation = CamList[camCount].transform.rotation;
        }

        if (Input.GetKeyDown(KeyCode.Alpha2))
        {
            if (camCount == (CamList.Length - 1))
            {
                camCount = 0;

            }
            else { camCount++; }
            gameObject.transform.position = CamList[camCount].transform.position;
            gameObject.transform.rotation = CamList[camCount].transform.rotation;
        }
    }
    void MouseView()
    {
        //this will be called every frame the mouse button is held
        float y = camSpeed * Input.GetAxis("Mouse X");
        rotate += camSpeed * Input.GetAxis("Mouse Y");

        rotate = Mathf.Clamp(rotate, minTurn, maxTurn);
        transform.eulerAngles = new Vector3(-rotate, transform.eulerAngles.y + y, 0);
    }
    void KeyMove()
    {
        Vector3 moveVector = new Vector3(0, 0, 0);
        moveVector.x = Input.GetAxis("Horizontal");
        moveVector.z = Input.GetAxis("Vertical");
        transform.Translate(moveVector * moveSpeed * Time.deltaTime);
    }



}
