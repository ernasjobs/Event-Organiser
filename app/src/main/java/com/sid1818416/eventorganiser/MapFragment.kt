package com.sid1818416.eventorganiser

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() {
    private val permisionFineLocation = Manifest.permission.ACCESS_FINE_LOCATION
    var fusedLocationClient: FusedLocationProviderClient? = null
    var locationRequest: LocationRequest? = null
    var callback: LocationCallback? = null
    private val Code_Permision = 100
    private var marcadorBellaVista: Marker? = null
    private lateinit var pocisionActual: LatLng
    private var PSailability: LatLng? = null


    private lateinit var Map: GoogleMap

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_map, container, false)

        fusedLocationClient = FusedLocationProviderClient(requireActivity())
        initLocationUser()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync {

                googleMap ->
            Map = googleMap

            Map.uiSettings.isZoomControlsEnabled = true
            Map.isMyLocationEnabled = true
            Map.uiSettings.isMyLocationButtonEnabled = true

            if (validationPermissionUbication()) {
                obtenerUbicacion()

            } else {
                GetPermission()
            }

            PSailability = LatLng(52.56557510, -0.31596920)

            marcadorBellaVista = Map.addMarker(
                MarkerOptions()
                    .position(PSailability!!)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                    .snippet("Peterborough Sailability Location")
                    .title("Peterborough Sailability")
            )
            marcadorBellaVista?.tag = 0
            // apiDirections()
        }


        return view
    }


    override fun onStart() {
        super.onStart()
        if (validationPermissionUbication()) {
            obtenerUbicacion()

        } else {
            GetPermission()
        }
    }

    override fun onPause() {
        super.onPause()
        detenerActualizacionUbicacion()
    }


    private fun validationPermissionUbication(): Boolean {

        val hayUbicacion = ActivityCompat.checkSelfPermission(
            requireActivity(),
            permisionFineLocation
        ) == PackageManager.PERMISSION_GRANTED

        return hayUbicacion
    }

    private fun GetPermission() {

        val racional = ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            permisionFineLocation
        )

        if (racional) {

            solicitudPermission()
        } else {
            solicitudPermission()
        }
    }

    private fun solicitudPermission() {
        requestPermissions(arrayOf(permisionFineLocation), Code_Permision)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            Code_Permision -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Permission Denied ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun obtenerUbicacion() {

        callback = object : LocationCallback() {
            override fun onLocationResult(ubicacionResult: LocationResult?) {
                super.onLocationResult(ubicacionResult)
                var run: Boolean = true
                for (ubicacion in ubicacionResult?.locations!!) {
                        if (run){
                            Toast.makeText(
                                requireContext(),
                                ubicacion.latitude.toString() + " , " + ubicacion.longitude.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        run = false

                    pocisionActual = LatLng(ubicacion.latitude, ubicacion.longitude)
                    Map.addMarker(
                        MarkerOptions()
                            .position(pocisionActual)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                            .title("Location...")
                            .snippet("This is your current location on the map")
                    )
                    Map.animateCamera(CameraUpdateFactory.newLatLngZoom(pocisionActual, 11.6f))

                }

                apiDirections(CurrentLocation = LatLng(pocisionActual.latitude, pocisionActual.longitude))

            }
        }

        fusedLocationClient?.requestLocationUpdates(locationRequest, callback, null)
    }

    private fun initLocationUser() {
        locationRequest = LocationRequest()
        //3000
        locationRequest?.interval = 10000000
        locationRequest?.fastestInterval = 10000000
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun detenerActualizacionUbicacion() {
        fusedLocationClient?.removeLocationUpdates(callback)
    }
    private fun apiDirections(CurrentLocation: LatLng){

        val destino: String = PSailability?.latitude.toString()+","+PSailability?.longitude.toString()

        val origin = CurrentLocation.latitude.toString() +","+CurrentLocation.longitude.toString()
        val destination = destino

        Log.d("Current Location", CurrentLocation.toString())

        val url = "https://maps.googleapis.com/maps/api/directions/json?origin=${origin}&destination=${destination}&sensor=false&mode=driving&key=AIzaSyB4YZrp7SHZzKWqo4qAiFxLlX5SKXBaS28"

        val queue = Volley.newRequestQueue(requireContext())
        val solicitud = StringRequest(Request.Method.GET, url,Response.Listener<String>{
                response ->  Log.d("request", response)

        }, Response.ErrorListener{
                error: VolleyError? -> Log.e("error", error.toString())
        })
        queue.add(solicitud)

    }
}