package kr.co.mlec.common;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * ���� ó�� Ŭ����
 * @author ������
 */
//@ControllerAdvice(annotations = Controller.class)
public class CommonExceptionAdvice {
    /**
     * ���� ������ ��ȯ
     * @param msg �����޼���
     * @return ����������
     */
    private ModelAndView showErrorPage(String msg) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/errorPage");
        mav.addObject("errorMsg", msg);
        return mav;
    }

    /**
     * �߸��� ��û �ڵ鸵
     * BindException �ڵ鸵
     * @return ����������
     */
    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException() {
        return showErrorPage("�߸��� ��û�Դϴ�.");
    }

    /**
     * ���� ������ �̻��� ��� ���� ������ ��ȯ
     * NumberFormatException �ڵ鸵
     * @return ����������
     */
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException() {
        return showErrorPage("���� ������ �̻��մϴ�.");
    }

    /**
     * DB���� Exception �ڵ鸵
     * @return ����������
     */
    @ExceptionHandler({SQLException.class, SQLIntegrityConstraintViolationException.class})
    public ModelAndView handleSQLException(Exception exception) {
        String msg;
        if(exception.getCause().toString().contains("link failure")){
            msg = "�����ͺ��̽� ���ῡ �����߽��ϴ�.";
        } else if(exception.getCause().toString().contains("Duplicate")){
            msg = "�̹� �����ϴ� �����Դϴ�.";
        } else {
            msg = "";
        }
        return showErrorPage(msg);
    }
}