from mathutils import Vector, Euler

class Cube:
    """
    Class defining model's cube.

    Blender's equivalent: Cube Object.
    """
    def __init__(self, name):
        """ x """
        self.name = name
        self.pos = Vector()
        self.rot = Euler()

    def setPos(self, pos):
        self.pos = pos

    def setRot(self, rot):
        self.rot = rot

    def toJson(self):
        cubeDict = dict()

        posDict=dict()
        posDict["x"] = self.pos.x
        posDict["y"] = self.pos.y
        posDict["z"] = self.pos.z

        rotDict=dict()
        rotDict["x"] = self.rot.x
        rotDict["y"] = self.rot.y
        rotDict["z"] = self.rot.z

        cubeDict["name"] = self.name
        cubeDict["pos"] = posDict
        cubeDict["rot"] = rotDict
        return cubeDict
