import imp

import bpy
import math

import cameraManager
import cubeManager

import time

def rotateCube(stepCount):
    for step in range(0, stepCount):
        originCube.rotation_euler[1] = math.radians(step * (360.0 / stepCount))
        scene.render.filepath = '/home/adamsko/VR/vr_shot_%d.jpg' % step
        bpy.ops.render.render( write_still=True )

def reset():
    imp.reload(cameraManager)
    imp.reload(cubeManager)
    camMgr = cameraManager.CameraManager()
    cubeMgr = cubeManager.CubeManager()
    
    camMgr.reset()
    cubeMgr.reset()

def start():
    # reload symbol table for Blender
    imp.reload(cameraManager)
    imp.reload(cubeManager)
    camMgr = cameraManager.CameraManager()
    cubeMgr = cubeManager.CubeManager()
    
    camMgr.reset()
    cubeMgr.reset()
    
    cubeMgr.testAnimate()
