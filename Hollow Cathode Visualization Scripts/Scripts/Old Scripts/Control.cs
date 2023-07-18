using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Control : MonoBehaviour
{
    [SerializeField] private Camera freecam;
    public Transform target;
    private Vector3 prev;
    private float tx, ty, tz;

    // Start is called before the first frame update
    void Start()
    {
        tx = target.position.x;
        ty = target.position.y;
        tz = target.position.z;
        Vector3 toTarget = new Vector3(tx, ty, tz);
        freecam.transform.Translate(toTarget);
        transform.LookAt(target);
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            prev = freecam.ScreenToViewportPoint(Input.mousePosition);
        }
        if (Input.GetMouseButton(0))
        {
            Vector3 dir = prev - freecam.ScreenToViewportPoint(Input.mousePosition);
            freecam.transform.position = target.position;
            freecam.transform.Rotate(new Vector3(1, 0, 0), dir.y * 180);
            freecam.transform.Rotate(new Vector3(0, 1, 0), -dir.x * 180, Space.World);
            freecam.transform.Translate(new Vector3(0, 0, -10));
            prev = freecam.ScreenToViewportPoint(Input.mousePosition);
        }
    }
}
