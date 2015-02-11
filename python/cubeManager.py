import imp

import bpy
import math
import mathutils
import time

class CubeManager:

    def __init__(self):
        self.mY = mathutils.Matrix.Rotation(math.radians(35.264), 4, 'Y')
        self.mZ = mathutils.Matrix.Rotation(math.radians(45), 4, 'Z')
        self.mScale = mathutils.Matrix.Scale(2, 4)
        self.matrixStart = self.mY * self.mZ * self.mScale
        self.cube = bpy.data.objects['Cube']

        self.angleRotStep = 9
        self.matrixRotStepX = mathutils.Matrix.Rotation(math.radians(self.angleRotStep), 4, 'X')
        self.matrixRotStepY = mathutils.Matrix.Rotation(math.radians(self.angleRotStep), 4, 'Y')
        self.matrixRotStepZ = mathutils.Matrix.Rotation(math.radians(self.angleRotStep), 4, 'Z')

    def reset(self):
        self.cube.matrix_world = self.matrixStart

    def shot(self, angleX, angleY, angleZ):
        scene = bpy.data.scenes["Scene"]
        scene.render.filepath = '/home/adamsko/cubes/cube_shot_%d_%d_%d.jpg' % (angleX, angleY, angleZ)
        bpy.ops.render.render( write_still=True )            

    def testAnimate(self):
        scene = bpy.data.scenes["Scene"]
        shotId = 0

        self.reset()
        for step in range(0, 10): # 90/angleRotStep
            self.shot(step, 0, 0)
            self.cube.matrix_world = self.cube.matrix_world * self.matrixRotStepX

        self.reset()
        for step in range(0, 10):
            self.shot(0, step, 0)
            self.cube.matrix_world = self.cube.matrix_world * self.matrixRotStepY

        self.reset()
        for step in range(0, 10):
            self.shot(0, 0, step)
            self.cube.matrix_world = self.cube.matrix_world * self.matrixRotStepZ
