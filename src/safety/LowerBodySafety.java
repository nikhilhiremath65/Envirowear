package safety;

import Backend.Data;

public class LowerBodySafety implements Safety{
        private float userSetInput;
        private float upperTempLimit;
        private float lowerTempLimit;
        private float timeLimit;
        private int nullCount,limitCount;

    public LowerBodySafety() {
        this.nullCount = 0;
        this.limitCount = 0;
    }

    public boolean checkUserSetTemp(){
            upperTempLimit = Data.getInstance().getUpperLimit();
            lowerTempLimit = Data.getInstance().getLowerLimit();
            userSetInput = Data.getInstance().getUserSetLower();
            return lowerTempLimit <= userSetInput && userSetInput <= upperTempLimit;
        }

        public float checkNullTemp(float sensorTemp){
            timeLimit = Data.getInstance().getTimeLimit();
            upperTempLimit = Data.getInstance().getUpperLimit();
            userSetInput = Data.getInstance().getUserSetUpper();
            if(nullCount < timeLimit){
                if(sensorTemp == 0){
                    sensorTemp = userSetInput;
                    nullCount++;
                }
                else{
                    nullCount = 0;
                }

            }

            else{
                Data.getInstance().setLowerStatus(false);
            }
            return sensorTemp;
        }

        public float checkSensorTempLimit(float sensorTemp){
            int timeLimit = Data.getInstance().getTimeLimit();
            float upperTempLimit = Data.getInstance().getUpperLimit();
            float lowerTempLimit = Data.getInstance().getLowerLimit();
            if(limitCount <= timeLimit){
                if(sensorTemp > upperTempLimit){
                    sensorTemp = upperTempLimit;
                    limitCount++;
                }
                else if(sensorTemp < lowerTempLimit){
                    sensorTemp = lowerTempLimit;
                    limitCount++;
                }
                else {
                    limitCount=0;

                }
            }
            else{
                Data.getInstance().setLowerStatus(false); // stop the whole device

            }
            return sensorTemp;
        }
}