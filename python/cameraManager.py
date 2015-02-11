import imp

import bpy
import math

class CameraManager:

    def __init__(self):
        return

    def reset(self):
        scene = bpy.data.scenes["Scene"]
        cam = bpy.data.objects['Camera']
        scene.camera.location.x = 8
        scene.camera.location.y = 0
        scene.camera.location.z = 0
        scene.camera.rotation_euler[0]=math.radians(90)
        scene.camera.rotation_euler[0]=math.radians(90)
        scene.camera.rotation_euler[1]=math.radians(0)
        scene.camera.rotation_euler[2]=math.radians(90)
        bpy.data.cameras['Camera'].type = 'ORTHO'
