package zl.wang.cn.com.wangmyapp.bean;

import java.util.List;

public class face {


    /**
     * code : 0
     * message : OK
     * data : {"session_id":"","image_height":200,"image_width":200,"face":[{"face_id":"2604491772208919076","x":81,"y":49,"height":56,"width":56,"pitch":12,"roll":5,"yaw":22,"age":23,"gender":0,"glass":false,"expression":27,"glasses":0,"mask":0,"hat":0,"beauty":100,"face_shape":{"face_profile":[{"x":80,"y":70},{"x":81,"y":75},{"x":82,"y":80},{"x":83,"y":86},{"x":86,"y":90},{"x":89,"y":95},{"x":93,"y":98},{"x":98,"y":101},{"x":103,"y":103},{"x":109,"y":103},{"x":114,"y":103},{"x":117,"y":100},{"x":118,"y":97},{"x":120,"y":93},{"x":121,"y":89},{"x":123,"y":86},{"x":124,"y":82},{"x":125,"y":78},{"x":125,"y":75},{"x":125,"y":71},{"x":125,"y":67}],"left_eye":[{"x":100,"y":69},{"x":102,"y":70},{"x":104,"y":71},{"x":106,"y":70},{"x":108,"y":70},{"x":107,"y":68},{"x":104,"y":67},{"x":102,"y":67}],"right_eye":[{"x":124,"y":66},{"x":123,"y":68},{"x":121,"y":69},{"x":119,"y":69},{"x":118,"y":69},{"x":118,"y":67},{"x":120,"y":66},{"x":122,"y":65}],"left_eyebrow":[{"x":94,"y":64},{"x":98,"y":63},{"x":102,"y":62},{"x":107,"y":62},{"x":111,"y":62},{"x":107,"y":60},{"x":102,"y":60},{"x":97,"y":61}],"right_eyebrow":[{"x":126,"y":61},{"x":124,"y":61},{"x":122,"y":62},{"x":120,"y":62},{"x":118,"y":63},{"x":119,"y":61},{"x":121,"y":59},{"x":124,"y":59}],"mouth":[{"x":105,"y":89},{"x":107,"y":91},{"x":110,"y":93},{"x":114,"y":93},{"x":115,"y":92},{"x":117,"y":91},{"x":118,"y":89},{"x":117,"y":88},{"x":116,"y":87},{"x":115,"y":88},{"x":113,"y":87},{"x":109,"y":88},{"x":108,"y":90},{"x":111,"y":90},{"x":114,"y":90},{"x":115,"y":90},{"x":117,"y":89},{"x":117,"y":89},{"x":115,"y":89},{"x":114,"y":90},{"x":111,"y":89},{"x":108,"y":89}],"nose":[{"x":116,"y":81},{"x":114,"y":69},{"x":113,"y":72},{"x":112,"y":76},{"x":111,"y":79},{"x":109,"y":82},{"x":113,"y":83},{"x":116,"y":83},{"x":118,"y":83},{"x":120,"y":81},{"x":119,"y":78},{"x":117,"y":75},{"x":115,"y":72}]}}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * session_id :
         * image_height : 200
         * image_width : 200
         * face : [{"face_id":"2604491772208919076","x":81,"y":49,"height":56,"width":56,"pitch":12,"roll":5,"yaw":22,"age":23,"gender":0,"glass":false,"expression":27,"glasses":0,"mask":0,"hat":0,"beauty":100,"face_shape":{"face_profile":[{"x":80,"y":70},{"x":81,"y":75},{"x":82,"y":80},{"x":83,"y":86},{"x":86,"y":90},{"x":89,"y":95},{"x":93,"y":98},{"x":98,"y":101},{"x":103,"y":103},{"x":109,"y":103},{"x":114,"y":103},{"x":117,"y":100},{"x":118,"y":97},{"x":120,"y":93},{"x":121,"y":89},{"x":123,"y":86},{"x":124,"y":82},{"x":125,"y":78},{"x":125,"y":75},{"x":125,"y":71},{"x":125,"y":67}],"left_eye":[{"x":100,"y":69},{"x":102,"y":70},{"x":104,"y":71},{"x":106,"y":70},{"x":108,"y":70},{"x":107,"y":68},{"x":104,"y":67},{"x":102,"y":67}],"right_eye":[{"x":124,"y":66},{"x":123,"y":68},{"x":121,"y":69},{"x":119,"y":69},{"x":118,"y":69},{"x":118,"y":67},{"x":120,"y":66},{"x":122,"y":65}],"left_eyebrow":[{"x":94,"y":64},{"x":98,"y":63},{"x":102,"y":62},{"x":107,"y":62},{"x":111,"y":62},{"x":107,"y":60},{"x":102,"y":60},{"x":97,"y":61}],"right_eyebrow":[{"x":126,"y":61},{"x":124,"y":61},{"x":122,"y":62},{"x":120,"y":62},{"x":118,"y":63},{"x":119,"y":61},{"x":121,"y":59},{"x":124,"y":59}],"mouth":[{"x":105,"y":89},{"x":107,"y":91},{"x":110,"y":93},{"x":114,"y":93},{"x":115,"y":92},{"x":117,"y":91},{"x":118,"y":89},{"x":117,"y":88},{"x":116,"y":87},{"x":115,"y":88},{"x":113,"y":87},{"x":109,"y":88},{"x":108,"y":90},{"x":111,"y":90},{"x":114,"y":90},{"x":115,"y":90},{"x":117,"y":89},{"x":117,"y":89},{"x":115,"y":89},{"x":114,"y":90},{"x":111,"y":89},{"x":108,"y":89}],"nose":[{"x":116,"y":81},{"x":114,"y":69},{"x":113,"y":72},{"x":112,"y":76},{"x":111,"y":79},{"x":109,"y":82},{"x":113,"y":83},{"x":116,"y":83},{"x":118,"y":83},{"x":120,"y":81},{"x":119,"y":78},{"x":117,"y":75},{"x":115,"y":72}]}}]
         */

        private String session_id;
        private int image_height;
        private int image_width;
        private List<FaceBean> face;

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public int getImage_height() {
            return image_height;
        }

        public void setImage_height(int image_height) {
            this.image_height = image_height;
        }

        public int getImage_width() {
            return image_width;
        }

        public void setImage_width(int image_width) {
            this.image_width = image_width;
        }

        public List<FaceBean> getFace() {
            return face;
        }

        public void setFace(List<FaceBean> face) {
            this.face = face;
        }

        public static class FaceBean {
            /**
             * face_id : 2604491772208919076
             * x : 81
             * y : 49
             * height : 56.0
             * width : 56.0
             * pitch : 12
             * roll : 5
             * yaw : 22
             * age : 23
             * gender : 0
             * glass : false
             * expression : 27
             * glasses : 0
             * mask : 0
             * hat : 0
             * beauty : 100
             * face_shape : {"face_profile":[{"x":80,"y":70},{"x":81,"y":75},{"x":82,"y":80},{"x":83,"y":86},{"x":86,"y":90},{"x":89,"y":95},{"x":93,"y":98},{"x":98,"y":101},{"x":103,"y":103},{"x":109,"y":103},{"x":114,"y":103},{"x":117,"y":100},{"x":118,"y":97},{"x":120,"y":93},{"x":121,"y":89},{"x":123,"y":86},{"x":124,"y":82},{"x":125,"y":78},{"x":125,"y":75},{"x":125,"y":71},{"x":125,"y":67}],"left_eye":[{"x":100,"y":69},{"x":102,"y":70},{"x":104,"y":71},{"x":106,"y":70},{"x":108,"y":70},{"x":107,"y":68},{"x":104,"y":67},{"x":102,"y":67}],"right_eye":[{"x":124,"y":66},{"x":123,"y":68},{"x":121,"y":69},{"x":119,"y":69},{"x":118,"y":69},{"x":118,"y":67},{"x":120,"y":66},{"x":122,"y":65}],"left_eyebrow":[{"x":94,"y":64},{"x":98,"y":63},{"x":102,"y":62},{"x":107,"y":62},{"x":111,"y":62},{"x":107,"y":60},{"x":102,"y":60},{"x":97,"y":61}],"right_eyebrow":[{"x":126,"y":61},{"x":124,"y":61},{"x":122,"y":62},{"x":120,"y":62},{"x":118,"y":63},{"x":119,"y":61},{"x":121,"y":59},{"x":124,"y":59}],"mouth":[{"x":105,"y":89},{"x":107,"y":91},{"x":110,"y":93},{"x":114,"y":93},{"x":115,"y":92},{"x":117,"y":91},{"x":118,"y":89},{"x":117,"y":88},{"x":116,"y":87},{"x":115,"y":88},{"x":113,"y":87},{"x":109,"y":88},{"x":108,"y":90},{"x":111,"y":90},{"x":114,"y":90},{"x":115,"y":90},{"x":117,"y":89},{"x":117,"y":89},{"x":115,"y":89},{"x":114,"y":90},{"x":111,"y":89},{"x":108,"y":89}],"nose":[{"x":116,"y":81},{"x":114,"y":69},{"x":113,"y":72},{"x":112,"y":76},{"x":111,"y":79},{"x":109,"y":82},{"x":113,"y":83},{"x":116,"y":83},{"x":118,"y":83},{"x":120,"y":81},{"x":119,"y":78},{"x":117,"y":75},{"x":115,"y":72}]}
             */

            private String face_id;
            private int x;
            private int y;
            private double height;
            private double width;
            private int pitch;
            private int roll;
            private int yaw;
            private int age;
            private int gender;
            private boolean glass;
            private int expression;
            private int glasses;
            private int mask;
            private int hat;
            private int beauty;
            private FaceShapeBean face_shape;

            public String getFace_id() {
                return face_id;
            }

            public void setFace_id(String face_id) {
                this.face_id = face_id;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }

            public double getWidth() {
                return width;
            }

            public void setWidth(double width) {
                this.width = width;
            }

            public int getPitch() {
                return pitch;
            }

            public void setPitch(int pitch) {
                this.pitch = pitch;
            }

            public int getRoll() {
                return roll;
            }

            public void setRoll(int roll) {
                this.roll = roll;
            }

            public int getYaw() {
                return yaw;
            }

            public void setYaw(int yaw) {
                this.yaw = yaw;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public boolean isGlass() {
                return glass;
            }

            public void setGlass(boolean glass) {
                this.glass = glass;
            }

            public int getExpression() {
                return expression;
            }

            public void setExpression(int expression) {
                this.expression = expression;
            }

            public int getGlasses() {
                return glasses;
            }

            public void setGlasses(int glasses) {
                this.glasses = glasses;
            }

            public int getMask() {
                return mask;
            }

            public void setMask(int mask) {
                this.mask = mask;
            }

            public int getHat() {
                return hat;
            }

            public void setHat(int hat) {
                this.hat = hat;
            }

            public int getBeauty() {
                return beauty;
            }

            public void setBeauty(int beauty) {
                this.beauty = beauty;
            }

            public FaceShapeBean getFace_shape() {
                return face_shape;
            }

            public void setFace_shape(FaceShapeBean face_shape) {
                this.face_shape = face_shape;
            }

            public static class FaceShapeBean {
                private List<FaceProfileBean> face_profile;
                private List<LeftEyeBean> left_eye;
                private List<RightEyeBean> right_eye;
                private List<LeftEyebrowBean> left_eyebrow;
                private List<RightEyebrowBean> right_eyebrow;
                private List<MouthBean> mouth;
                private List<NoseBean> nose;

                public List<FaceProfileBean> getFace_profile() {
                    return face_profile;
                }

                public void setFace_profile(List<FaceProfileBean> face_profile) {
                    this.face_profile = face_profile;
                }

                public List<LeftEyeBean> getLeft_eye() {
                    return left_eye;
                }

                public void setLeft_eye(List<LeftEyeBean> left_eye) {
                    this.left_eye = left_eye;
                }

                public List<RightEyeBean> getRight_eye() {
                    return right_eye;
                }

                public void setRight_eye(List<RightEyeBean> right_eye) {
                    this.right_eye = right_eye;
                }

                public List<LeftEyebrowBean> getLeft_eyebrow() {
                    return left_eyebrow;
                }

                public void setLeft_eyebrow(List<LeftEyebrowBean> left_eyebrow) {
                    this.left_eyebrow = left_eyebrow;
                }

                public List<RightEyebrowBean> getRight_eyebrow() {
                    return right_eyebrow;
                }

                public void setRight_eyebrow(List<RightEyebrowBean> right_eyebrow) {
                    this.right_eyebrow = right_eyebrow;
                }

                public List<MouthBean> getMouth() {
                    return mouth;
                }

                public void setMouth(List<MouthBean> mouth) {
                    this.mouth = mouth;
                }

                public List<NoseBean> getNose() {
                    return nose;
                }

                public void setNose(List<NoseBean> nose) {
                    this.nose = nose;
                }

                public static class FaceProfileBean {
                    /**
                     * x : 80
                     * y : 70
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }

                public static class LeftEyeBean {
                    /**
                     * x : 100
                     * y : 69
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }

                public static class RightEyeBean {
                    /**
                     * x : 124
                     * y : 66
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }

                public static class LeftEyebrowBean {
                    /**
                     * x : 94
                     * y : 64
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }

                public static class RightEyebrowBean {
                    /**
                     * x : 126
                     * y : 61
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }

                public static class MouthBean {
                    /**
                     * x : 105
                     * y : 89
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }

                public static class NoseBean {
                    /**
                     * x : 116
                     * y : 81
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }
            }
        }
    }
}
