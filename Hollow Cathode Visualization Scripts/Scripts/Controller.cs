using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Controller : MonoBehaviour
{
    [SerializeField] private Camera freecam;
    public Transform target;
    public float view_distance;
    public bool autorotate = true;
    private Vector3 prev;
    private float tx, ty, tz;
    private Vector3 dir;
    public starttour st;
    public GameObject camBtn;
    
    // Start is called before the first frame update
    void Start()
    {   
        
            if (!autorotate)
            {//this will run on start of active cathode scene, need to launch sound file
                st.startIntro();
                //launch intro audio here
            }
        
        tx = target.position.x;
        ty = target.position.y;
        tz = target.position.z;
        Vector3 toTarget = new Vector3(tx, ty, tz);
        freecam.transform.position = target.position;
        freecam.transform.Translate(0, 0, view_distance);
        transform.LookAt(target);
        dir = prev - freecam.ScreenToViewportPoint(Input.mousePosition);
    }

    // Update is called once per frame
    void Update()
    {

        if (autorotate == false)
        {

            if(!st.tour){
                camBtn.SetActive(false);
                if (Input.GetMouseButtonDown(0))
                {
                    prev = freecam.ScreenToViewportPoint(Input.mousePosition);
                }
                if (Input.GetMouseButton(0))
                {
                    dir = prev - freecam.ScreenToViewportPoint(Input.mousePosition);
                    freecam.transform.position = target.position;
                    freecam.transform.Rotate(new Vector3(1, 0, 0), dir.y * 180);
                    freecam.transform.Rotate(new Vector3(0, 1, 0), -dir.x * 180, Space.World);
                    freecam.transform.Translate(new Vector3(0, 0, view_distance));
                    prev = freecam.ScreenToViewportPoint(Input.mousePosition);
                }
                if (Input.mouseScrollDelta.y > 0 && view_distance <= -35f)
                {
                    freecam.transform.position = Vector3.MoveTowards(freecam.transform.position, target.transform.position, Input.mouseScrollDelta.y * 0.75f);
                    transform.LookAt(target);
                    view_distance += Input.mouseScrollDelta.y * 0.75f;
                    prev = freecam.transform.position;
                }
                if (Input.mouseScrollDelta.y < 0 && view_distance >= -75f)
                {
                    freecam.transform.position = Vector3.MoveTowards(freecam.transform.position, target.transform.position, Input.mouseScrollDelta.y * 0.75f);
                    transform.LookAt(target);
                    view_distance += Input.mouseScrollDelta.y * 0.75f;
                    prev = freecam.transform.position;
                }
           }
        else{
            if(!st.moving && !st.in_intro){
                
                if (Input.GetMouseButton(0)){//tour cam controls
                    float lr = Input.GetAxis("Mouse X") * .5f;
                    float ud = Input.GetAxis("Mouse Y") * .5f;

                    if(freecam.transform.position.x <= st.limxpos && lr < 0){
                        freecam.transform.Translate(new Vector3(-lr, 0, 0));
                    }
                    if(freecam.transform.position.x >= st.limxneg && lr > 0){
                        freecam.transform.Translate(new Vector3(-lr, 0, 0));
                    }
                    if(freecam.transform.position.y <= st.limypos && ud < 0){
                        freecam.transform.Translate(new Vector3(0, -ud, 0));
                    }
                    if(freecam.transform.position.y >= st.limyneg && ud > 0){
                        freecam.transform.Translate(new Vector3(0, -ud, 0)); 
                    }
                    //cam.transform.Translate(new Vector3(-lr, -ud, 0));
                    transform.LookAt(target);
                    prev = freecam.transform.position;
                } 
            }
        }
    }
        if (autorotate == true)
        {
            freecam.transform.position = target.position;
            freecam.transform.Rotate(new Vector3(0, 1, 0), 0.1f, Space.World);
            freecam.transform.Translate(new Vector3(0, 0, view_distance));



        }


    }
}


///4.581617 48.3278 -12.66766