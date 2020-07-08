package springbootexample.zhangqin.soft.vo;

public class QpsInfoVO implements Comparable{
    private Long name;
    private Long value;

    public QpsInfoVO(Long name, Long value) {
        this.name = name;
        this.value = value;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        QpsInfoVO qpsInfoVO = (QpsInfoVO) o;
        Long value = this.getName();
        Long value1 = qpsInfoVO.getName();
        long l = value - value1;
        if(l==0){
            return 0;
        }else if(l<0){
            return -1;
        }else{
            return 1;
        }
    }
}
