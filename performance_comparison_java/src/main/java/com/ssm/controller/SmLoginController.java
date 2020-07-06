package com.ssm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.entity.PcUser;
import com.ssm.entity.SmAdvice;
import com.ssm.entity.SmCheck;
import com.ssm.entity.SmClean;
import com.ssm.entity.SmLogin;
import com.ssm.service.ISmAdviceService;
import com.ssm.service.ISmCheckService;
import com.ssm.service.ISmCleanService;
import com.ssm.service.ISmLoginService;
import com.ssm.vo.res.SysResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 人员信息
 * </p>
 *
 * @author hello
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/user")
public class SmLoginController {
	@Autowired
	private ISmLoginService iSmLoginService;
	@Autowired
	private ISmAdviceService iSmAdviceService;
	@Autowired
	private ISmCheckService iSmCheckService;
	@Autowired
	private ISmCleanService iSmCleanService;
	
	@PostMapping("/login")
	public SysResult login(@RequestBody SmLogin pcUser, HttpServletRequest request, HttpServletResponse response) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("code", pcUser.getCode()).eq("password", pcUser.getPassword());
			queryWrapper.eq("role", pcUser.getRole());
			List<SmLogin> list = iSmLoginService.list(queryWrapper);
//			HttpSession session=request.getSession();
//			String verifyCode = (String) session.getAttribute("RWEERSFDSDFSDFSD");
//			String verifyCodes =(String) request.getSession().getAttribute("RWEERSFDSDFSDFSD");
			if (CollectionUtils.isEmpty(list)) {
				return SysResult.fail("用户名与密码不匹配！");
			}
			return SysResult.ok(list.get(0));
		} catch (Exception e) {
			return SysResult.fail("用户名与密码不匹配！");
		}
	}

	@PostMapping("/register")
	public SysResult register(@RequestBody SmLogin pcUser) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("code", pcUser.getCode());
			List<SmLogin> list = iSmLoginService.list(queryWrapper);
			if (list.size() > 0) {
				return SysResult.fail("账号重复！");
			}
			pcUser.setStatus("Y");
			pcUser.setRole("2");
			pcUser.setCreateDate(new Date());
			pcUser.setProfee(2500);
			pcUser.setHeatfee(2200);
			pcUser.setWaterfee(1800);
			pcUser.setAllNum(6500);
			pcUser.setCarMoney(100000);
			pcUser.setCarStatus("N");
			pcUser.setFeeStatus("N");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			pcUser.setEndDate(sdf.format(new Date()));
			iSmLoginService.save(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("注册失败！");
		}

	}
	
	@PostMapping("/passwordEdit")
	public SysResult passwordEdit(@RequestBody PcUser pcUser) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("code", pcUser.getCode());
			List<SmLogin> list = iSmLoginService.list(queryWrapper);
			if (list.size() > 0) {
				SmLogin login = list.get(0);
				if(login.getPassword().equals(pcUser.getPwd())) {
					login.setPassword(pcUser.getNewPwd());
					iSmLoginService.saveOrUpdate(login);
					return SysResult.ok();
				}else {
					return SysResult.fail("原密码错误！");
				}
			}else {
				return SysResult.fail("修改失败！");
			}
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}

	}

	// 员工列表
	@PostMapping("/adminUser")
	public SysResult adminUser(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			if (StringUtils.isNotEmpty(pc.getDuty())) {
				queryWrapper.like("work", pc.getDuty());
			}
			// 员工3 业主2 admin 1
			queryWrapper.eq("role", "3");
			queryWrapper.eq("status", "Y");
			Page<SmLogin> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmLogin> list = iSmLoginService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}

	}

	// 员工添加
	@PostMapping("/adminUserAdd")
	public SysResult adminUserAdd(@RequestBody SmLogin pcUser) {
		try {
			pcUser.setStatus("Y");
			pcUser.setRole("3");
			pcUser.setCreateDate(new Date());
			iSmLoginService.save(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("添加失败！");
		}

	}

	// 员工修改
	@PostMapping("/adminUserUpdate")
	public SysResult adminUserUpdate(@RequestBody SmLogin pcUser) {
		try {
			SmLogin user = iSmLoginService.getById(pcUser.getId());
			user.setPassword(pcUser.getPassword());
			user.setName(pcUser.getName());
			user.setIdCard(pcUser.getIdCard());
			user.setMobile(pcUser.getMobile());
			user.setWork(pcUser.getWork());
			user.setOrg(pcUser.getOrg());
			iSmLoginService.saveOrUpdate(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}
	}

	// 业主列表
	@PostMapping("/ownerUser")
	public SysResult ownerUser(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			if (StringUtils.isNotEmpty(pc.getMobile())) {
				queryWrapper.like("mobile", pc.getMobile());
			}
			// 员工3 业主2 admin 1
			queryWrapper.eq("role", "2");
			queryWrapper.eq("status", "Y");
			Page<SmLogin> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmLogin> list = iSmLoginService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}

	}

	// 业主添加
	@PostMapping("/ownerUserAdd")
	public SysResult ownerUserAdd(@RequestBody SmLogin pcUser) {
		try {
			pcUser.setStatus("Y");
			pcUser.setRole("2");
			pcUser.setCreateDate(new Date());
			iSmLoginService.save(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("添加失败！");
		}

	}

	// 业主修改
	@PostMapping("/ownerUserUpdate")
	public SysResult ownerUserUpdate(@RequestBody SmLogin pcUser) {
		try {
			SmLogin user = iSmLoginService.getById(pcUser.getId());
			user.setPassword(pcUser.getPassword());
			user.setName(pcUser.getName());
			user.setIdCard(pcUser.getIdCard());
			user.setMobile(pcUser.getMobile());
			user.setAddress(pcUser.getAddress());
			iSmLoginService.saveOrUpdate(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}
	}

	// 提醒服务列表
	@PostMapping("/warn")
	public SysResult warn(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			// 员工3 业主2 admin 1
			queryWrapper.eq("role", "2");
			queryWrapper.eq("status", "Y");
			Page<SmLogin> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmLogin> list = iSmLoginService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}

	}

	// 建议列表
	@PostMapping("/adviceList")
	public SysResult adviceList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmAdvice> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			if (StringUtils.isNotEmpty(pc.getCode())) {
				queryWrapper.eq("code", pc.getCode());
			}
			// 员工3 业主2 admin 1
			queryWrapper.eq("status", "Y");
			Page<SmAdvice> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmAdvice> list = iSmAdviceService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 建议添加
	@PostMapping("/adviceAdd")
	public SysResult adviceAdd(@RequestBody SmAdvice pcUser) {
		try {
			pcUser.setStatus("Y");
			pcUser.setCreateDate(new Date());
			iSmAdviceService.save(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("添加失败！");
		}

	}

	// 岗位管理
	@PostMapping("/workList")
	public SysResult workList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			if (StringUtils.isNotEmpty(pc.getOrg())) {
				queryWrapper.eq("org", pc.getOrg());
			}
			// 员工3 业主2 admin 1
			queryWrapper.eq("role", "3");
			queryWrapper.eq("status", "Y");
			Page<SmLogin> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmLogin> list = iSmLoginService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 岗位修改
	@PostMapping("/workUpdate")
	public SysResult workupdate(@RequestBody SmLogin pcUser) {
		try {
			SmLogin user = iSmLoginService.getById(pcUser.getId());
			user.setWork(pcUser.getWork());
			user.setOrg(pcUser.getOrg());
			iSmLoginService.saveOrUpdate(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}

	}

	// 考勤管理
	@PostMapping("/checkList")
	public SysResult checkList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmCheck> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			if (StringUtils.isNotEmpty(pc.getOrg())) {
				queryWrapper.eq("org", pc.getOrg());
			}
			queryWrapper.eq("status", "Y");
			Page<SmCheck> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmCheck> list = iSmCheckService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 考勤修改
	@PostMapping("/checkUpdate")
	public SysResult checkUpdate(@RequestBody SmCheck pcUser) {
		try {
			SmCheck user = iSmCheckService.getById(pcUser.getId());
			user.setAbsentNum(pcUser.getAbsentNum());
			user.setEarlyNum(pcUser.getEarlyNum());
			user.setLateNum(pcUser.getLateNum());
			iSmCheckService.saveOrUpdate(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}
	}

	// 员工列表
	@PostMapping("/personList")
	public SysResult personList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			if (StringUtils.isNotEmpty(pc.getOrg())) {
				queryWrapper.eq("org", pc.getOrg());
			}
			queryWrapper.eq("role", "3");
			queryWrapper.eq("status", "Y");
			Page<SmLogin> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmLogin> list = iSmLoginService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 员工离职
	@PostMapping("/personGo")
	public SysResult personGo(@RequestBody SmLogin pc) {
		try {
			SmLogin user = iSmLoginService.getById(pc.getId());
			user.setStatus("N");
			iSmLoginService.saveOrUpdate(user);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("离职失败！");
		}
	}

	// 保洁列表
	@PostMapping("/cleanList")
	public SysResult cleanList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmClean> queryWrapper = new QueryWrapper<>();
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			if (StringUtils.isNotEmpty(pc.getOrg())) {
				queryWrapper.eq("org", pc.getOrg());
			}
			queryWrapper.eq("status", "Y");
			Page<SmClean> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmClean> list = iSmCleanService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 保洁添加
	@PostMapping("/cleanAdd")
	public SysResult cleanAdd(@RequestBody SmClean pcUser) {
		try {
			pcUser.setStatus("Y");
			pcUser.setCreateDate(new Date());
			iSmCleanService.save(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("添加失败！");
		}

	}

	// 考勤修改
	@PostMapping("/cleanUpdate")
	public SysResult cleanUpdate(@RequestBody SmClean pcUser) {
		try {
			SmClean user = iSmCleanService.getById(pcUser.getId());
			user.setAddress(pcUser.getAddress());
			user.setWorkTime(pcUser.getWorkTime());
			user.setEndTime(pcUser.getEndTime());
			iSmCleanService.saveOrUpdate(pcUser);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}
	}

}
