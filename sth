
{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": 1,
   "metadata": {},
   "outputs": [],
   "source": [
    "import cv2\n",
    "import matplotlib.pyplot as plt\n",
    "import numpy as np\n",
    "BODY_PARTS={\"Head\": 0, \"Neck\": 1, \"RShoulder\": 2, \"RElbow\": 3, \"RWrist\": 4,\n",
    "\"LShoulder\": 5, \"LElbow\": 6, \"LWrist\": 7, \"RHip\": 8, \"RKnee\": 9,\n",
    "\"RAnkle\": 10, \"LHip\": 11, \"LKnee\": 12, \"LAnkle\": 13, \"Chest\": 14,\n",
    "\"Background\": 15}\n",
    "BODY_PAIRS = [ [\"Head\", \"Neck\"], [\"Neck\", \"RShoulder\"], [\"RShoulder\", \"RElbow\"],\n",
    "             [\"RElbow\", \"RWrist\"], [\"Neck\", \"LShoulder\"], [\"LShoulder\", \"LElbow\"],\n",
    "             [\"LElbow\", \"LWrist\"], [\"Neck\", \"Chest\"], [\"Chest\", \"RHip\"], [\"RHip\", \"RKnee\"],\n",
    "             [\"RKnee\", \"RAnkle\"], [\"Chest\", \"LHip\"], [\"LHip\", \"LKnee\"], [\"LKnee\", \"LAnkle\"] ]\n",
    "threshold=0.5"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 2,
   "metadata": {},
   "outputs": [],
   "source": [
    "protoTxt = \"/media/magda/DANE/zajecia/MMI/lab4/OpenPose/pose_deploy_linevec_faster_4_stages.prototxt\"\n",
    "wagi = \"/media/magda/DANE/zajecia/MMI/lab4/OpenPose/pose_iter_160000.caffemodel\"\n",
    "\n",
    "# wczytanie sieci\n",
    "net = cv2.dnn.readNetFromCaffe(protoTxt, wagi)\n",
    "\n",
    "frame = cv2.imread(\"/media/magda/DANE/zajecia/MMI/lab4/obrazy/6_a.jpg\") "
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 3,
   "metadata": {},
   "outputs": [],
   "source": [
    "frameWidth= frame.shape[1]\n",
    "frameHeight= frame.shape[0]\n",
    "# rozmiar input\n",
    "inHeight = 368\n",
    "inWidth = int((inHeight/frameHeight)*frameWidth)\n",
    "\n",
    "# przygotowanie obrazu\n",
    "inpBlob = cv2.dnn.blobFromImage(frame, 1.0 / 255, (inWidth, inHeight), (0, 0, 0), swapRB=False, crop=False)\n",
    "\n",
    "# ustawienie jako input\n",
    "net.setInput(inpBlob)\n",
    "#predykcja\n",
    "output = net.forward()"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 4,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "27"
      ]
     },
     "execution_count": 4,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "#rysowanie punktów na obrazie\n",
    "keypointNo=output.shape[1]\n",
    "frame2=frame.copy()\n",
    "\n",
    "H = output.shape[2]\n",
    "W = output.shape[3]\n",
    "# pusta lista do przechowywania punktów\n",
    "points = []\n",
    "\n",
    "for i in range(keypointNo):\n",
    "    # confidence map dla każdego punktu\n",
    "    probMap = output[0, i, :, :]\n",
    "\n",
    "    # szukanie maximum na probMap\n",
    "    minVal, prob, minLoc, point = cv2.minMaxLoc(probMap)\n",
    "\n",
    "    # skalowanie - do wyświetlenia\n",
    "    x = (frameWidth * point[0]) / W\n",
    "    y = (frameHeight * point[1]) / H\n",
    "\n",
    "    if prob > threshold :\n",
    "        cv2.circle(frame2, (int(x), int(y)), 5, (0, 255, 255), thickness=2, lineType=cv2.FILLED)\n",
    "        cv2.putText(frame2, \"{}\".format(i), (int(x), int(y)), cv2.FONT_HERSHEY_SIMPLEX, 0.8, (0, 0, 255), 3, lineType=cv2.LINE_AA)\n",
    "\n",
    "        points.append((int(x), int(y)))\n",
    "    else :\n",
    "        points.append(None)\n",
    "\n",
    "cv2.imshow(\"Keypoints\",frame2)\n",
    "cv2.waitKey(0)\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": [
    "#tworzenie szkieletu używając listy points\n",
    "frame3=frame.copy()\n",
    "\n",
    "for pair in BODY_PAIRS:\n",
    "    #pierwsza część pary\n",
    "    start = pair[0]\n",
    "    #druga część pary\n",
    "    end = pair[1]\n",
    "  \n",
    "#tu wpisz swój kod\n",
    " "
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 5,
   "metadata": {},
   "outputs": [],
   "source": [
    "#Nałożenie jednego punktu wg confidence map na obraz\n",
    "frame4=frame.copy()\n",
    "pkt = 5  #left Shoulder\n",
    "probMap = output[0, pkt, :, :]\n",
    "probMap = cv2.resize(probMap, (frameWidth, frameHeight))\n",
    "probMap_n = np.abs(probMap*255)\n",
    "\n",
    "probMap2 = cv2.cvtColor(probMap_n,cv2.COLOR_GRAY2RGB)\n",
    "probMap2[:,:,0]=0\n",
    "probMap2[:,:,1]=0\n",
    "\n",
    "frame4=cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)\n",
    "#nałożenie obrazu i probMap2\n",
    "frame5 = cv2.addWeighted(frame4, 0.4, probMap2, 0.6, 0, dtype=cv2.CV_32F).astype(np.uint8)\n",
    "\n",
    "cv2.imshow(\"Confidence Map\",probMap)\n",
    "cv2.imshow(\"One Keypoint\",frame5)\n",
    "\n",
    "cv2.waitKey(0)\n",
    "cv2.destroyAllWindows()"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Python 3",
   "language": "python",
   "name": "python3"
  },
  "language_info": {
   "codemirror_mode": {
    "name": "ipython",
    "version": 3
   },
   "file_extension": ".py",
   "mimetype": "text/x-python",
   "name": "python",
   "nbconvert_exporter": "python",
   "pygments_lexer": "ipython3",
   "version": "3.6.9"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 2
}
